package io.springbatch.springbatchlecture.batch.chunk.writer;

import io.springbatch.springbatchlecture.batch.domain.ApiRequestVO;
import io.springbatch.springbatchlecture.batch.domain.ApiResponseVO;
import io.springbatch.springbatchlecture.service.AbstractApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

@Slf4j
public class ApiItemWriter2 extends FlatFileItemWriter<ApiRequestVO> {

    private AbstractApiService apiService;

    public ApiItemWriter2(AbstractApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void write(List<? extends ApiRequestVO> items) throws Exception {

        System.out.println("----------------------------------");
        items.forEach(item -> System.out.println("items = " + item));
        System.out.println("----------------------------------");

        ApiResponseVO response = apiService.service(items);
        System.out.println("response = " + response);

        items.forEach(item -> item.setApiResponseVO(response));

        super.setResource(new FileSystemResource("C:\\jsw\\inflearn\\spring-batch-lecture\\src\\main\\resources\\product2.txt"));
        super.open(new ExecutionContext());
        super.setLineAggregator(new DelimitedLineAggregator<>());
        super.setAppendAllowed(true);
        super.write(items);
    }
}
