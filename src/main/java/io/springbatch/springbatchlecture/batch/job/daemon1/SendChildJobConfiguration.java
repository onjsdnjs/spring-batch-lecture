package io.springbatch.springbatchlecture.batch.job.daemon1;

import io.springbatch.springbatchlecture.batch.tasklet.send.SendDataPostTasklet;
import io.springbatch.springbatchlecture.batch.tasklet.send.SendDataPreTasklet;
import io.springbatch.springbatchlecture.batch.tasklet.send.SendDataTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * io.anymobi.core.batch.job.send
 * ㄴ SendDataChildJobConfiguration.java
 * </pre>
 * 부모 배치 Job 의 자식 Job 클래스로서 대상자 데이터를 조회하고 가공 처리하는 로직을 수행한다
 *
 * @author : soowon.jung
 * @version : 1.0.0
 * @date : 2021-07-22 오후 1:34
 * @see :
 **/

@Configuration
@RequiredArgsConstructor
public class SendChildJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final SendDataTasklet sendDataTasklet;
    private final SendDataPreTasklet sendDataPreTasklet;
    private final SendDataPostTasklet sendDataPostTasklet;
    private final StepExecutionListener dataSendStepListener;
    private final Step apiMasterStep;
    private final JobLauncher jobLauncher;

    @Bean
    public Step jobStep() throws Exception {
        return stepBuilderFactory.get("jobStep")
                .job(childJob())
                .launcher(jobLauncher)
                .build();
    }

    @Bean
    public Job childJob() throws Exception {
        return jobBuilderFactory.get("childJob")
                .incrementer(new RunIdIncrementer())
//                .listener(new DataSendChildJobListener(sharedObject))
                .start(childStep1())
                .next(childStep2())
                .next(apiMasterStep)
                .next(childStep3())
                .build();
    }

    @Bean
    public Step childStep1() throws Exception {
        return stepBuilderFactory.get("childStep1")
                .tasklet(sendDataPreTasklet)
                .build();
    }

    @Bean
    public Step childStep2() throws Exception {
        return stepBuilderFactory.get("childStep2")
                .tasklet(sendDataTasklet)
                .listener(dataSendStepListener)
                .build();
    }

    @Bean
    public Step childStep3() throws Exception {
        return stepBuilderFactory.get("childStep3")
                .tasklet(sendDataPostTasklet)
                .build();
    }


}