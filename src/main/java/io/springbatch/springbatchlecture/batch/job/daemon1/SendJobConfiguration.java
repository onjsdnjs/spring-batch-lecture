package io.springbatch.springbatchlecture.batch.job.daemon1;

import io.springbatch.springbatchlecture.batch.domain.ApiRequestVO;
import io.springbatch.springbatchlecture.batch.tasklet.ServiceEndTasklet;
import io.springbatch.springbatchlecture.batch.tasklet.ServiceStartTasklet;
import io.springbatch.springbatchlecture.service.SendDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * <pre>
 * io.anymobi.core.batch.job.send
 * ㄴ SendDataJobConfiguration.java
 * </pre>
 * 배치 Job 을 구성하는 클래스로서 각 단계별로 로직을 수행하도록 구성되어 있음
 *
 * @author : soowon.jung
 * @version : 1.0.0
 * @date : 2021-07-22 오후 1:30
 * @see :
 **/

@Configuration
@RequiredArgsConstructor
public class SendJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ServiceStartTasklet serviceStartTasklet;
    private final ServiceEndTasklet serviceEndTasklet;
    private final Step jobStep;
    private final SendDataService<List<? extends ApiRequestVO>> dataSenderService;

    @Bean
    @Qualifier("sendDataJob")
    public Job dataSendJob() throws Exception {

        return jobBuilderFactory.get("dataSendJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .on("FAILED").end()
                .from(step1()).on("*").to(jobStep)
                .from(jobStep).on("FAILED").end()
                .next(step2())
                .end()
                .build();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .tasklet(serviceStartTasklet)
                .build();
    }

    @Bean
    public Step step2() throws Exception {
        return stepBuilderFactory.get("step2")
                .tasklet(serviceEndTasklet)
                .build();
    }
}
