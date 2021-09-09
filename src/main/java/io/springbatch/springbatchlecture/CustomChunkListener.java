package io.springbatch.springbatchlecture;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.AfterChunkError;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Component
public class CustomChunkListener {

	private int i; // 공유 데이터

	@BeforeChunk
	public void beforeChunk(ChunkContext context) {
		System.out.println(">> Before the chunk : "+ context.getStepContext().getStepExecution().getStepName());
		Object count = context.getStepContext().getStepExecution().getExecutionContext().get("count");
		if(count == null){
			context.getStepContext().getStepExecution().getExecutionContext().putInt("count", ++i);
		}
	}

	@AfterChunk
	public void afterChunk(ChunkContext context) {
		System.out.println(">> After the chunk : "+ context.getStepContext().getStepExecution().getStepName());
		int count = (int)context.getStepContext().getStepExecution().getExecutionContext().get("count");
		System.out.println(">> count : "+ count);
	}

	@AfterChunkError
	public void afterChunkError(ChunkContext context) {
		System.out.println(">> After the chunk : "+ context.getStepContext().getStepExecution().getStepName());
		int count = (int)context.getStepContext().getStepExecution().getExecutionContext().get("count");
		System.out.println(">> count : "+ count);
	}
}
