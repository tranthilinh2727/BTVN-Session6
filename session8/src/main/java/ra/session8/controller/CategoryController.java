// Khai báo package chứa controller
package ra.session8.controller;

// Import các lớp cần thiết
import ra.session8.model.entity.Category; // Entity đại diện cho bảng Category trong DB
import ra.session8.Service.CategoryService; // Service xử lý logic nghiệp vụ liên quan đến Category
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Dùng để truyền dữ liệu sang view (HTML)
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // Dùng để truyền thông báo khi redirect

import java.util.ArrayList;
import java.util.List;

// Đánh dấu đây là một Controller trong Spring MVC
@Controller
// Định nghĩa đường dẫn gốc cho tất cả các phương thức trong class này
@RequestMapping("/categories")
public class CategoryController {

    // Tự động inject CategoryService để sử dụng các phương thức xử lý nghiệp vụ
    @Autowired
    private CategoryService categoryService;

    // Xử lý yêu cầu GET đến "/categories" để hiển thị danh sách danh mục
    @GetMapping
    public String getCategories(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "5") int size,
                                @RequestParam(defaultValue = "") String searchName,
                                Model model) {
        // Lấy danh sách danh mục theo trang, kích thước và từ khóa tìm kiếm
        List<Category> categories = categoryService.findAll(page, size, searchName);

        // Đếm tổng số phần tử phù hợp với từ khóa tìm kiếm
        long totalElements = categoryService.countTotalElement(searchName);

        // Tính tổng số trang cần hiển thị
        int totalPages = (int) Math.ceil((double) totalElements / size);

        // Tạo danh sách số trang để hiển thị phân trang
        List<Integer> pages = new ArrayList<Integer>();
        for (int i = 1 ; i <= totalPages ; i++) {
            pages.add(i);
        }

        // Truyền dữ liệu sang view
        model.addAttribute("categories", categories);
        model.addAttribute("searchName", searchName);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("pages", pages);

        // Trả về tên view (categories.html)
        return "categories";
    }

    // Hiển thị form thêm mới danh mục
    @GetMapping("/add")
    public String addCategory(Model model) {
        // Truyền một đối tượng Category rỗng sang view để binding với form
        model.addAttribute("category", new Category());
        return "add-category";
    }

    // Xử lý form thêm mới danh mục (POST)
    @PostMapping
    public String saveCategory(@ModelAttribute Category category, Model model ,RedirectAttributes redirectAttributes) {
        // Gọi service để lưu danh mục
        boolean rs = categoryService.saveCategory(category);

        // Nếu lưu thất bại, hiển thị lại form và thông báo lỗi
        if(!rs){
            model.addAttribute("category", category);
            model.addAttribute("message","Có lỗi , thêm danh mục không thành công !");
            return "add-category";
        } else {
            // Nếu thành công, redirect về danh sách và hiển thị thông báo
            redirectAttributes.addFlashAttribute("message","Thêm mới danh mục thành công !");
            return "redirect:/categories";
        }
    }

    // Hiển thị form chỉnh sửa danh mục
    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable Long id, Model model) {
        // Tìm danh mục theo id để hiển thị lên form
        Category category = categoryService.findById(id); // Cần có phương thức findById trong service
        model.addAttribute("category", category);
        return "edit-category";
    }

    // Xử lý form cập nhật danh mục
    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute Category category, RedirectAttributes redirectAttributes, Model model) {
        // Gọi service để cập nhật danh mục
        boolean rs = categoryService.updateCategory(id, category);

        // Nếu thất bại, hiển thị lại form và thông báo lỗi
        if(!rs){
            model.addAttribute("category", category);
            model.addAttribute("message","Có lỗi , sửa thông tin không thành công !");
            return "edit-category";
        } else {
            // Nếu thành công, redirect về danh sách và hiển thị thông báo
            redirectAttributes.addFlashAttribute("message","Cập nhật danh mục thành công !");
            return "redirect:/categories";
        }
    }

    // Xử lý yêu cầu xóa danh mục
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id , RedirectAttributes redirectAttributes) {
        // Gọi service để xóa danh mục theo id
        boolean rs = categoryService.deleteCategoryById(id);

        // Thêm thông báo tương ứng
        if(rs){
            redirectAttributes.addFlashAttribute("message" , "Xóa danh mục thành công !");
        } else {
            redirectAttributes.addFlashAttribute("message" , "Xóa danh mục không thành công !");
        }

        // Redirect về danh sách danh mục
        return "redirect:/categories";
    }
}