package com.avitepa.foundation.bank.exceptionhandling;

public class AccountServiceException extends Exception {

    private static final long serialVersionUID = -470180507998010368L;

    public AccountServiceException() {
        super();
    }

    public AccountServiceException(final String message) {
        super(message);
    }
}
