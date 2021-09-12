package io.springbatch.springbatchlecture.batch.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiRequestVO {
    private Long id;
    private String url;
    private String ContentType;
    private String HttpMethod;
    private ProductVO productVO;

}
