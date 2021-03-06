package org.dorkmaster.flow.spring.exception;

import org.dorkmaster.flow.exception.FactoryException;

public class UndefinedBeanException extends FactoryException {
    public UndefinedBeanException() {
    }

    public UndefinedBeanException(String message) {
        super(message);
    }

    public UndefinedBeanException(String message, Throwable cause) {
        super(message, cause);
    }

    public UndefinedBeanException(Throwable cause) {
        super(cause);
    }

    public UndefinedBeanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
