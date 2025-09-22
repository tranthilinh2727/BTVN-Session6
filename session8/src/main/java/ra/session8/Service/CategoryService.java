package ra.session8.Service;

import ra.session8.model.entity.Category;
import ra.session8.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service  // Đánh dấu đây là tầng Service để Spring quản lý (Bean)
public class CategoryService {

    // Tự động inject CategoryRepository để gọi xuống DB
    @Autowired
    private CategoryRepository categoryRepository;

    // -------------------- LẤY DANH SÁCH CATEGORY --------------------
    @Transactional(readOnly = true)  // Chỉ đọc dữ liệu, không thay đổi gì trong DB
    public List<Category> findAll(int page , int size , String search) {
        // Tính vị trí bắt đầu (offset) dựa vào số trang và số phần tử mỗi trang
        int offset = (page - 1) * size;
        // Gọi xuống repository để lấy danh sách Category với phân trang + tìm kiếm
        return categoryRepository.getAllCategories(offset, size, search);
    }

    // -------------------- LẤY CATEGORY THEO ID --------------------
    @Transactional(readOnly = true)
    public Category findById(Long id) {
        // Trả về một Category theo id, hoặc null nếu không tồn tại
        return categoryRepository.getCategoryById(id);
    }

    // -------------------- ĐẾM TỔNG CATEGORY --------------------
    @Transactional(readOnly = true)
    public Long countTotalElement(String search){
        // Đếm tổng số Category (có hỗ trợ tìm kiếm) -> dùng cho phân trang
        return categoryRepository.countTotalElement(search);
    }
    // -------------------- THÊM MỚI CATEGORY --------------------
    @Transactional
    public boolean saveCategory(Category category) {
        // Gọi repository để lưu (insert nếu chưa có id, update nếu đã có id)
        return categoryRepository.saveCategory(category);
    }

    // -------------------- THÊM MỚI CATEGORY --------------------
    @Transactional
    public boolean updateCategory(long id ,Category category) {
        Category oldCategory = categoryRepository.getCategoryById(id);
        if (oldCategory != null) {
            category.setId(oldCategory.getId());
            return categoryRepository.updateCategory(category);
        }else {
            return false;
        }

    }

    @Transactional
    public boolean deleteCategoryById(long id) {
        return categoryRepository.deleteCategoryById(id);
    }}
