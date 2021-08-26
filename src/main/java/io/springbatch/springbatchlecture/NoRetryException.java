
package io.springbatch.springbatchlecture;

public class NoRetryException extends RuntimeException {

	public NoRetryException() {
		super();
	}

	public NoRetryException(String msg) {
		super(msg);
	}
}
