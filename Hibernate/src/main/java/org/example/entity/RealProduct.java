package org.example.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("REAL")
public class RealProduct extends BaseProduct{

    private float weight;
    private int width;
    private int length;
    private int height;
}


