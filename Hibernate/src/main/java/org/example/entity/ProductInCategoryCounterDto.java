package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductInCategoryCounterDto {
    private Long categoryId;
    private Long counter;
}
