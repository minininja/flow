package org.dorkmaster.flow.exception;

public class InvalidClassDeclarationException extends FactoryException {
    public InvalidClassDeclarationException() {
    }

    public InvalidClassDeclarationException(String message) {
        super(message);
    }

    public InvalidClassDeclarationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidClassDeclarationException(Throwable cause) {
        super(cause);
    }

    public InvalidClassDeclarationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
