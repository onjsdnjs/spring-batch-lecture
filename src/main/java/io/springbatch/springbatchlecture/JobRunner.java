package io.springbatch.springbatchlecture;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JobRunner implements ApplicationRunner {

    @Autowired
    private Job fileJob;

    @Autowired
    private Job apiJob;

    @Autowired
    private JobLauncher jobLauncher;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        JobParameters jobParameters = new JobParametersBuilder().toJobParameters();

        jobLauncher.run(fileJob, jobParameters);
        jobLauncher.run(apiJob, jobParameters);

    }
}
