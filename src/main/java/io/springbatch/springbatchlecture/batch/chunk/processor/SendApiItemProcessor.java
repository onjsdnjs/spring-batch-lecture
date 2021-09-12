package io.springbatch.springbatchlecture.batch.chunk.processor;

import io.springbatch.springbatchlecture.batch.domain.ApiRequestVO;
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
public class SendApiItemProcessor implements ItemProcessor<ApiRequestVO, ApiRequestVO> {

    @Override
    public ApiRequestVO process(ApiRequestVO item) throws Exception {
        System.out.println(">> 8.SendApiItemProcessor : item = " + item);
        // Business Logic
        return item;
    }
}
