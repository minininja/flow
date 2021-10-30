package org.dorkmaster.flow.exception;

public class NoTasksException extends ConfigurationException {
    public NoTasksException() {
    }

    public NoTasksException(String message) {
        super(message);
    }

    public NoTasksException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoTasksException(Throwable cause) {
        super(cause);
    }

    public NoTasksException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
