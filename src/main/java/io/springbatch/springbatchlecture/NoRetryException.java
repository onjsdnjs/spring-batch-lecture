
package io.springbatch.springbatchlecture;

public class NoRetryException extends Exception {

	public NoRetryException() {
		super();
	}

	public NoRetryException(String msg) {
		super(msg);
	}
}
