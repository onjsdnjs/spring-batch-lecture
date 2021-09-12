package io.springbatch.springbatchlecture.batch.chunk.writer;

import io.springbatch.springbatchlecture.batch.domain.ApiRequestVO;
import io.springbatch.springbatchlecture.batch.domain.ApiResponseVO;
import io.springbatch.springbatchlecture.service.AbstractApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
public class ApiItemWriter1 implements ItemWriter<ApiRequestVO> {

    private AbstractApiService apiService;

    public ApiItemWriter1(AbstractApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void write(List<? extends ApiRequestVO> items) throws Exception {

        System.out.println("----------------------------------");
        items.forEach(item -> System.out.println("items = " + item));
        System.out.println("----------------------------------");

        ApiResponseVO response = apiService.service(items);
        System.out.println("response = " + response);
    }
}
