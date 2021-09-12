package io.springbatch.springbatchlecture.service;

import io.springbatch.springbatchlecture.batch.domain.ProductVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiInfoVO {

    private String uri;
    private String method;
    private String contentType;
    private List<ProductVO> productList;
}
