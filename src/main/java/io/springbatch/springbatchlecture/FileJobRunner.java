package io.springbatch.springbatchlecture;

import io.springbatch.springbatchlecture.scheduler.FileSchJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static org.quartz.JobBuilder.newJob;

@Component
public class FileJobRunner extends JobRunner {

    @Autowired
    private Scheduler scheduler;

    @Override
    protected void doRun(ApplicationArguments args) {

        String[] sourceArgs = args.getSourceArgs();
        JobDetail jobDetail = buildJobDetail(FileSchJob.class, "fileJob", "batch", new HashMap());
        jobDetail.getJobDataMap().put("requestDate", sourceArgs[0]);

        try {
            scheduler.scheduleJob(jobDetail, buildJobTrigger("0/50 * * * * ?"));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
