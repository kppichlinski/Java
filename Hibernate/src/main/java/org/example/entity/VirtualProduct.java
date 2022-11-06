package org.example.entity;

import lombok.*;
import org.example.types.ProductType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("VIRTUAL")
public class VirtualProduct extends BaseProduct {

    private boolean downloadable;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_name")
    private String fileName;
}


