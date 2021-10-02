package org.dorkmaster.flow.exception;

public class MalformedYamlException extends FactoryException {
    public MalformedYamlException() {
    }

    public MalformedYamlException(String message) {
        super(message);
    }

    public MalformedYamlException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedYamlException(Throwable cause) {
        super(cause);
    }

    public MalformedYamlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
