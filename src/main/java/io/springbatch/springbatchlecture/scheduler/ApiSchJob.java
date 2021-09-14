package io.springbatch.springbatchlecture.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ApiSchJob extends QuartzJobBean{

	@Autowired
	private Job apiJob;

	@Autowired
	private JobLauncher jobLauncher;

	@SneakyThrows
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		JobParameters jobParameters = new JobParametersBuilder()
									.addLong("id", new Date().getTime())
									.toJobParameters();
		jobLauncher.run(apiJob, jobParameters);
	}
}
