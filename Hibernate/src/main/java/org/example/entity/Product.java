package org.example.entity;

import lombok.*;
import org.example.types.ProductType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProductType productType;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Set<Review> reviews;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "attribute_id")}
    )
    @Builder.Default
    private Set<Attribute> attributes = new HashSet<>();

    public void addAttributes(Attribute attribute) {
        attributes.add(attribute);
        attribute.getProducts().add(this);
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setProduct(this);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", price=" + price +
                ", productType=" + productType +
                '}';
    }
}


