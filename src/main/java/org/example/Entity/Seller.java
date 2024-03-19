package org.example.Entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int sellerId;
    public String sellerName;
    @OneToMany
    @JoinColumn(name="product_fk")
    public List<Product> products;
}
