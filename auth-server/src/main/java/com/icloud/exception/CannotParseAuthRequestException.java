package com.icloud.exception;

public class CannotParseAuthRequestException extends RuntimeException {

    public CannotParseAuthRequestException() {
        super();
    }

    public CannotParseAuthRequestException(String message) {
        super(message);
    }

    public CannotParseAuthRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotParseAuthRequestException(Throwable cause) {
        super(cause);
    }

    protected CannotParseAuthRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
