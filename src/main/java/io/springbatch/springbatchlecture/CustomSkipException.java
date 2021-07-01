package io.springbatch.springbatchlecture;

public class CustomSkipException extends Exception {

    public CustomSkipException() {
        super();
    }

    public CustomSkipException(String message) {
        super(message);
    }
}
