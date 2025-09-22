package ra.session8.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor  //Tạo tất cả contructor có đầy đủ tham số
@NoArgsConstructor  // Tạo Contructor ko tham số
@Data  // Tự động sinh Getter Setter To string
@Builder //Cho phép dùng BuilderParter để tạo đối tượng
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    //id là khóa chính tự động tăng t
    private Long    id;
    private String  cateName;
    private String  description;
    private boolean status;
}
