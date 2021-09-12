package io.springbatch.springbatchlecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {

    private Long id;
    private String name;
    private int price;
    private String type;
}
