package io.springbatch.springbatchlecture.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class BatchSchJob extends QuartzJobBean{

	@Autowired
	private Job fileJob;

	@Autowired
	private Job apiJob;

	@Autowired
	private JobLauncher jobLauncher;

	@SneakyThrows
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		JobParameters jobParameters = new JobParametersBuilder()
									.addLong("date", new Date().getTime())
									.toJobParameters();

        jobLauncher.run(fileJob, jobParameters);
		jobLauncher.run(apiJob, jobParameters);
	}

}
