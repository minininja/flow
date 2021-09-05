package org.dorkmaster.flow.exception;

public class DuplicateFlowException extends FactoryException {
    public DuplicateFlowException() {
    }

    public DuplicateFlowException(String message) {
        super(message);
    }

    public DuplicateFlowException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateFlowException(Throwable cause) {
        super(cause);
    }

    public DuplicateFlowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
