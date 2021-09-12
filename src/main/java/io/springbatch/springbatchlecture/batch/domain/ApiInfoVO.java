package io.springbatch.springbatchlecture.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiInfoVO {

    private String apiCd;
    private String uri;
    private String method;
    private String contentType;
    private String queryString;
    private String vendorPk;
    private String secret;
}
