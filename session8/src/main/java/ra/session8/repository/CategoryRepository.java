package ra.session8.repository;

import ra.session8.model.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository // Đánh dấu đây là 1 "DAO/Repository" để Spring quản lý (tầng thao tác DB)
public class CategoryRepository {
    @Autowired
    private SessionFactory sessionFactory; // Được Spring tiêm vào (Bean Hibernate), để mở kết nối DB

    public List<Category> getAllCategories(int offset, int limit, String searchName) {
        // Mở 1 session (tương tác với DB)
        Session session = sessionFactory.openSession();

        // Tạo HQL query, có điều kiện search (nếu search null thì lấy tất cả)
        List<Category> categories = session.createQuery(
                        "FROM Category c WHERE (:search is null or c.cateName LIKE :search )", Category.class)
                // Gán giá trị cho biến :search (nếu searchName="điện" -> LIKE '%điện%')
                .setParameter("search","%"+searchName+"%")
                // offset = bỏ qua bao nhiêu bản ghi đầu
                .setFirstResult(offset)
                // limit = lấy tối đa bao nhiêu bản ghi
                .setMaxResults(limit)
                .getResultList(); // thực thi query, trả về list Category

        session.close(); // đóng session (tránh rò rỉ kết nối)
        return categories;
    }

    /**
     * Đếm tổng số Category thỏa điều kiện search
     * Dùng cho tính tổng số trang khi phân trang
     */
    public long countTotalElement(String search){
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery(
                            "select count(c) from Category c where (:search is null or c.cateName like :search)",
                            Long.class
                    )
                    .setParameter("search","%"+search+"%")
                    .uniqueResult(); // trả về đúng 1 giá trị (long)
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Lưu mới hoặc cập nhật Category
     */
    public Boolean saveCategory(Category category) {
        Session session = sessionFactory.openSession();
        try {
            // saveOrUpdate = nếu id null thì INSERT, có id thì UPDATE
            session.saveOrUpdate(category);
            session.close();
            return true;
        }catch (Exception e) {
            session.close();
            return false;
        }
    }

    /**
     * Cập nhật Category (sử dụng Transaction để đảm bảo an toàn)
     */
    @Transactional
    public boolean updateCategory(Category category) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction(); // bắt đầu transaction
            session.update(category); // update dữ liệu
            transaction.commit(); // xác nhận thay đổi
            return true;
        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback(); // rollback nếu có lỗi
            }
            return false;
        }finally {
            session.close(); // luôn đóng session
        }
    }

    public Category getCategoryById(long id) {
        Session session = sessionFactory.openSession();
        try {
            Category category = session.get(Category.class, id); // lấy object theo primary key
            session.close();
            return category;
        }catch (Exception e) {
            session.close();
            return null;
        }
    }

    /**
     * Xóa Category theo id
     */
    public boolean deleteCategoryById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            // lấy Category theo id
            Category category = getCategoryById(id);
            if(category != null) {
                session.delete(category); // xóa category
                transaction.commit(); // xác nhận xóa
                return true;
            }else {
                transaction.rollback(); // nếu không tồn tại thì rollback
                return false;
            }
        }catch (Exception e) {
            return false;
        }finally {
            session.close();
        }
    }
}
