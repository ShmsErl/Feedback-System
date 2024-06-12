package com.tobeto.feedback_system.core.results;

public class ErrorsResult extends Result {


    public ErrorsResult(String message) {
        super(message, false);
    }

    public ErrorsResult() {
        super(false);
    }
}
