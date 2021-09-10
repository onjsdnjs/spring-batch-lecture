package io.springbatch.springbatchlecture;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBatchTest
@RunWith(SpringRunner.class)
@SpringBootTest(classes={JobOperatorConfiguration.class, TestBatchConfig.class})
public class SimpleJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void simple_job_테스트() throws Exception {

        // given
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("requestDate", "20020101")
                .addLong("date", new Date().getTime())
                .toJobParameters();

        // when
//        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1");

        // then
        Assert.assertEquals(jobExecution.getStatus(), BatchStatus.COMPLETED);
        StepExecution stepExecution = (StepExecution)((List) jobExecution.getStepExecutions()).get(0);

        Assert.assertEquals(stepExecution.getCommitCount(), 11);
        Assert.assertEquals(stepExecution.getWriteCount(), 1000);
        Assert.assertEquals(stepExecution.getWriteCount(), 1000);
    }

    @After
    public void clear() throws Exception {
        jdbcTemplate.execute("delete from customer2");
    }
}