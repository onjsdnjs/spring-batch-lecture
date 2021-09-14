package io.springbatch.springbatchlecture;

import io.springbatch.springbatchlecture.scheduler.ApiSchJob;
import io.springbatch.springbatchlecture.scheduler.FileSchJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ApiJobRunner extends JobRunner {

    @Autowired
    private Scheduler scheduler;

    @Override
    protected void doRun(ApplicationArguments args) {

        JobDetail jobDetail = buildJobDetail(ApiSchJob.class, "apiJob", "batch", new HashMap());

        try {
            scheduler.scheduleJob(jobDetail, buildJobTrigger("0/30 * * * * ?"));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
