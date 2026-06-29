package com.nbu.bank_crm.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public class LoanResponse {
    private final BigDecimal amount;
    private final int installmentsCount;
    private final LocalDate conclusionDate;
    private final String loanType;
}
