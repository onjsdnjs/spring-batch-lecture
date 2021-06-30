package io.springbatch.springbatchlecture;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Component
public class CustomItemReadListener implements ItemReadListener {

	@Override
	public void beforeRead() {
		System.out.println(">> beforeRead"); // item 이 없을 때까지 반복하므로 최종 한번 더 호출된다
	}

	@Override
	public void afterRead(Object item) {
		System.out.println(">> afterRead : "+ item);
	}

	@Override
	public void onReadError(Exception ex) {
		System.out.println(">> onReadError : " + ex.getMessage());
	}
}
