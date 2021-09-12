package io.springbatch.springbatchlecture.batch.chunk.processor;

import io.springbatch.springbatchlecture.batch.domain.ApiRequestVO;
import io.springbatch.springbatchlecture.batch.domain.ProductVO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ApiItemProcessor2 implements ItemProcessor<ProductVO, ApiRequestVO> {

    @Override
    public ApiRequestVO process(ProductVO productVO) throws Exception {

        return ApiRequestVO.builder()
                .url("http://localhost:8080/api/product/2")
                .ContentType("application/json")
                .HttpMethod("POST")
                .productVO(productVO)
                .build();
    }
}
