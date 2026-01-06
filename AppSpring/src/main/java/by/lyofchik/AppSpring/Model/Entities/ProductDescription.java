package by.lyofchik.AppSpring.Model.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_description")
public class ProductDescription {
    @Id
    @Column(name = "product_id", nullable = false)
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Size(max = 1000)
    @Column(name = "description", length = 1000)
    private String description;

}