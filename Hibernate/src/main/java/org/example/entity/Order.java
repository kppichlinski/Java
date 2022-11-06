package org.example.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@NamedEntityGraph(
        name = "order-rows",
        attributeNodes = {
                @NamedAttributeNode(value = "orderRows", subgraph = "orderRows"),
                @NamedAttributeNode("customer")
        }, subgraphs = @NamedSubgraph(
        name = "orderRows",
        attributeNodes = @NamedAttributeNode("product"))
)
@Entity
@Table(name = "\"order\"")
@Cacheable
@Cache(region = "order", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime created;
    private BigDecimal total;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private Set<OrderRow> orderRows;

    @ManyToOne
    Customer customer;

    private String uuid = UUID.randomUUID().toString();

    @Version
    private long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(uuid, order.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", created=" + created +
                ", total=" + total +
                ", uuid='" + uuid + '\'' +
                ", version=" + version +
                '}';
    }
}


