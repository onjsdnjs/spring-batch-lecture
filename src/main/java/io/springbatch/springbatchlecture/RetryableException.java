
package io.springbatch.springbatchlecture;

public class RetryableException extends Exception {

	public RetryableException() {
		super();
	}

	public RetryableException(String msg) {
		super(msg);
	}
}
