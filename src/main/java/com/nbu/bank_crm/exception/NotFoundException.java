package com.nbu.bank_crm.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Class<?> entityClass, Long id) {
        super(entityClass.getSimpleName() + " with id " + id + " not found");
    }

    //for client concrete classes and account
    //identifier is PIN or UIC or IBAN
    public NotFoundException(Class<?> entityClass, String identifier) {
        super(entityClass.getSimpleName() + " with identifier " + identifier + " not found");
    }
}
