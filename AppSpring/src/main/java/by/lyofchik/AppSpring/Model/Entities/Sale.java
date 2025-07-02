package by.lyofchik.AppSpring.Model.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Builder
@Data
@Entity
@Table(name = "sales")
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saleid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "productid", nullable = false)
    private Product product;

    @ColumnDefault("getDate()")
    @Column(name = "saledate", nullable = false)
    private LocalDate saleDate;

}
