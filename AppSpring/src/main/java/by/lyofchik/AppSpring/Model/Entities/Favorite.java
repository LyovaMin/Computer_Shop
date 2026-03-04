package by.lyofchik.AppSpring.Model.Entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "favorites")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Favorite {
    @Id
    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

}