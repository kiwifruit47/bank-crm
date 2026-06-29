package com.nbu.bank_crm.dto.response;

import lombok.Builder;

@Builder
public class ErrorResponse {
    private final String error;
    private final int status;
    private final String message;
}
