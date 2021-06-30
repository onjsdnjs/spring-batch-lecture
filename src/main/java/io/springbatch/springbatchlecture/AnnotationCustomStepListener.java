package io.springbatch.springbatchlecture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;

public class AnnotationCustomStepListener {

    @BeforeStep
    public void beforeStep(StepExecution stepExecution){
        System.out.println("@stepExecution.getStepName() : " + stepExecution.getStepName());
    }

    @AfterStep
    public void afterStep(StepExecution stepExecution){
        System.out.println("@stepExecution.getStatus() : " + stepExecution.getStatus());
    }
}
