package io.springbatch.springbatchlecture;

import org.springframework.batch.core.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomJobParametersValidator implements JobParametersValidator {

    @Override
    public void validate(JobParameters jobParameters) throws JobParametersInvalidException {
        if (jobParameters.getString("name") == null) {
            throw new JobParametersInvalidException("name parameter is not found.");
        }
    }
}


