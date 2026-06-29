package com.nbu.bank_crm.dto.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class ClientAccountResponse {
    private final String IBAN;
    private final BigDecimal balance;
}
