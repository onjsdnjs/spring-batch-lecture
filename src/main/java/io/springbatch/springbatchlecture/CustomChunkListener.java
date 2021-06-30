package io.springbatch.springbatchlecture;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

public class CustomChunkListener {

	private int i;

	@BeforeChunk
	public void beforeChunk(ChunkContext context) {
		System.out.println(">> Before the chunk : "+ context.getStepContext().getStepExecution().getStepName());
		context.getStepContext().getStepExecutionContext().put("count", i++);
	}

	@AfterChunk
	public void afterChunk(ChunkContext context) {
		System.out.println(">> After the chunk : "+ context.getStepContext().getStepExecution().getStepName());
		int count = (int)context.getStepContext().getStepExecutionContext().get("count");
		System.out.println(">> count : "+ count);
	}
}
