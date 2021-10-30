package org.dorkmaster.flow.exception;

public class MisconfiguredDecidersException extends ConfigurationException {
    public MisconfiguredDecidersException() {
    }

    public MisconfiguredDecidersException(String message) {
        super(message);
    }

    public MisconfiguredDecidersException(String message, Throwable cause) {
        super(message, cause);
    }

    public MisconfiguredDecidersException(Throwable cause) {
        super(cause);
    }

    public MisconfiguredDecidersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
