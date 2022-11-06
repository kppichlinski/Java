package org.example.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_row")
public class OrderRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;

    @OneToOne(fetch = FetchType.LAZY)
    private Product product;

    @Override
    public String toString() {
        return "OrderRow{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}


