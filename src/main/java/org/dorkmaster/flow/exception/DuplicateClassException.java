package org.dorkmaster.flow.exception;

public class DuplicateClassException extends ConfigurationException {
    public DuplicateClassException() {
    }

    public DuplicateClassException(String message) {
        super(message);
    }

    public DuplicateClassException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateClassException(Throwable cause) {
        super(cause);
    }

    public DuplicateClassException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
