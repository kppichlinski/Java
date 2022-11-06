package org.example.entity.batch;

import lombok.*;
import org.example.entity.Product;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "review")
@Entity
public class BatchReview {

    @Id
    private Long id;

    private String content;
    private int rating;

    @Column(name = "product_id")
    private long productId;

    @Override
    public String toString() {
        return "BatchReview{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                '}';
    }
}
