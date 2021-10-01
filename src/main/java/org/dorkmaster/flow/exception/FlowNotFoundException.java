package org.dorkmaster.flow.exception;

public class FlowNotFoundException extends BaseException {
    public FlowNotFoundException() {
    }

    public FlowNotFoundException(String message) {
        super(message);
    }

    public FlowNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowNotFoundException(Throwable cause) {
        super(cause);
    }

    public FlowNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
