package ra.session8.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name="books")

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;
    private String title;
    private String author;
    private double price;
    @ManyToOne //(quan hệ nhiều sách thuộc về 1 danh mục)
    @JoinColumn(name="category_id")  //liên kết với cọt id bản category
    private Category category; //Liên kết với entityCategory
}
