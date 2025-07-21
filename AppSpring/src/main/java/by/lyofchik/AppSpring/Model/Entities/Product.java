package by.lyofchik.AppSpring.Model.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid", nullable = false)
    private Integer id;

    @Column(name = "productname", nullable = false, length = 64)
    private String productName;

    @Column(name = "price", nullable = false)
    private Integer price;

    //@BatchSize(size = 10)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "categoryid", nullable = false)
    private Category category;
}
