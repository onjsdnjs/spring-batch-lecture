package io.springbatch.springbatchlecture.batch.chunk.writer;

import io.springbatch.springbatchlecture.batch.domain.ApiRequestVO;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Slf4j
public class SendApiItemWriter implements ItemWriter<ApiRequestVO> {

//    private final BeanObjectFactory serviceBeanFactory;

    @Override
    public void write(List<? extends ApiRequestVO> items) throws Exception {

        System.out.println("");
        System.out.println(">> 9.SendApiItemWriter is started");

        System.out.println("----------------------------------");

        items.forEach(item -> System.out.println("items = " + item));

        System.out.println("----------------------------------");
        System.out.println("");

//        AbstractDataService<List<? extends ApiRequestVO>> service = serviceBeanFactory.getService(Constants.TASK_CD.TASK_DATA_SND);
        // 발송 완료 response 가지고 옴
//        service.service(items);


    }
}
