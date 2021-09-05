package org.dorkmaster.flow.exception;

public class NoDecidersException extends ConfigurationException {
    public NoDecidersException() {
    }

    public NoDecidersException(String message) {
        super(message);
    }

    public NoDecidersException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDecidersException(Throwable cause) {
        super(cause);
    }

    public NoDecidersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
