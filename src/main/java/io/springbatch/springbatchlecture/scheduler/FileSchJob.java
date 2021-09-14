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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Component
@Slf4j
public class FileSchJob extends QuartzJobBean{

	@Autowired
	private Job fileJob;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JobExplorer jobExplorer;

	@SneakyThrows
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		String requestDate = (String)context.getJobDetail().getJobDataMap().get("requestDate");

		JobParameters jobParameters = new JobParametersBuilder()
									.addLong("id", new Date().getTime())
									.addString("requestDate", requestDate)
									.toJobParameters();

		int jobInstanceCount = jobExplorer.getJobInstanceCount(fileJob.getName());
		List<JobInstance> jobInstances = jobExplorer.getJobInstances(fileJob.getName(), 0, jobInstanceCount);

		if(jobInstances.size() > 0) {
			for(JobInstance jobInstance : jobInstances){
				List<JobExecution> jobExecutions = jobExplorer.getJobExecutions(jobInstance);
				List<JobExecution> jobExecutionList = jobExecutions.stream().filter(jobExecution ->
								jobExecution.getJobParameters().getString("requestDate").equals(requestDate))
						.collect(Collectors.toList());
				if (jobExecutionList.size() > 0) {
					throw new JobExecutionException(requestDate + " already exists");
				}
			}
		}

		jobLauncher.run(fileJob, jobParameters);
	}

}
