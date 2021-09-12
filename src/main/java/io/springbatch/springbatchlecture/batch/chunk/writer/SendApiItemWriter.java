package io.springbatch.springbatchlecture.batch.chunk.writer;

import io.springbatch.springbatchlecture.batch.domain.ApiRequestVO;
import io.springbatch.springbatchlecture.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * <pre>
 * io.anymobi.core.batch.chunk.api.writer
 * ㄴ SendApiItemWriter.java
 * </pre>
 * ItemProcessor 로 부터 전달 받은 데이터를 처리하는 클래스
 *
 * @author : soowon.jung
 * @version : 1.0.0
 * @date : 2021-07-22 오후 1:27
 * @see :
 **/

@Component
@Slf4j
public class SendApiItemWriter implements ItemWriter<ApiRequestVO> {

    private final ApiService apiService;

    public SendApiItemWriter(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void write(List<? extends ApiRequestVO> items) throws Exception {

        System.out.println("");
        System.out.println(">> 9.SendApiItemWriter is started");

        System.out.println("----------------------------------");

        items.forEach(item -> System.out.println("items = " + item));

        System.out.println("----------------------------------");
        System.out.println("");

        apiService.service(items);


    }
}
