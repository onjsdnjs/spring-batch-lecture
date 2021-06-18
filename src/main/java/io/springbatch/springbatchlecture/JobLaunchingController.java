/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.springbatch.springbatchlecture;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class JobLaunchingController {

	@Autowired
	private Job job;

	@Autowired
	private BasicBatchConfigurer basicBatchConfigurer;

	@PostMapping(value = "/batch")
	public void launch(@RequestBody Member member) throws Exception {

		JobParameters jobParameters = new JobParametersBuilder()
						.addString("id", member.getId())
						.addDate("date", new Date())
						.toJobParameters();

		SimpleJobLauncher jobLauncher = (SimpleJobLauncher)basicBatchConfigurer.getJobLauncher();
		jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
		jobLauncher.run(job, jobParameters);

		System.out.println("Job is completed");
	}
}
