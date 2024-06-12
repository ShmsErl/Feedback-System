package com.tobeto.feedback_system.core.results;


public class ErrorsDataResult<T> extends DataResult<T> {


    public ErrorsDataResult(T data, String message) {
        super(data, message, false);
    }

    public ErrorsDataResult(T data) {
        super(data, false);
    }


    public ErrorsDataResult(String message) {
        super(null, message, false);
    }


}
