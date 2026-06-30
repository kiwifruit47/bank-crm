package com.nbu.bank_crm.exception;

public class InstallmentCountExceedingMaxException extends RuntimeException {
    public InstallmentCountExceedingMaxException(int maxInstallmentCount) {
        super("The requested installments exceed the maximum count of " + maxInstallmentCount);
    }
}
