package io.springbatch.springbatchlecture;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBatchTest
@RunWith(SpringRunner.class)
@SpringBootTest(classes={SimpleJobConfiguration.class, TestBatchConfig.class})
public class SimpleJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void simple_job_테스트() throws Exception {

        // given
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("requestDate", "20020101")
                .toJobParameters();

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        // then
        Assert.assertEquals(jobExecution.getStatus(), BatchStatus.COMPLETED);
    }
}