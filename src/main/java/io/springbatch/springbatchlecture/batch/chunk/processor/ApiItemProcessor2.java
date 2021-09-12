package io.springbatch.springbatchlecture.batch.chunk.processor;

import io.springbatch.springbatchlecture.batch.domain.ApiRequestVO;
import io.springbatch.springbatchlecture.batch.domain.ProductVO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * io.anymobi.core.batch.chunk.api.processor
 * ㄴ SendApiItemProcessor.java
 * </pre>
 * ItemReader 로 부터 전달 받은 데이터를 가공하는 클래스
 *
 * @author : soowon.jung
 * @version : 1.0.0
 * @date : 2021-07-22 오후 1:25
 * @see :
 **/

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
