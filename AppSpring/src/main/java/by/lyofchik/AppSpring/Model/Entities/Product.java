package by.lyofchik.AppSpring.Model.Entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.BatchSize;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Integer id;

    @Column(name = "product_name", nullable = false, length = 64)
    private String productName;

    @Column(name = "price", nullable = false)
    private Double price;

    //@BatchSize(size = 10)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "quantity", nullable = false)
    private int quantity;

//    @OneToOne(fetch = FetchType.EAGER, optional = true)
//    @JoinColumn(name = "product_description", nullable = true)
//    private ProductDescription productDescription;
}
