package com.nbu.bank_crm.exception;

import java.math.BigDecimal;

public class AmountExceedingMaxException extends RuntimeException {
    public AmountExceedingMaxException(BigDecimal max) {
        super("The requested loan amount exceeds the maximum of " + max.toString());
    }
}
