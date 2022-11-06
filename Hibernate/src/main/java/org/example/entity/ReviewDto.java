package org.example.entity;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDto {

    private Long id;
    private String content;
    private int rating;

}
