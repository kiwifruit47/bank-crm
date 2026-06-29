package com.nbu.bank_crm.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.YearMonth;

@Builder
public class InstallmentResponse {
    private final YearMonth month;
    private final BigDecimal amount;
    private final BigDecimal principalAmount;
    private final BigDecimal interestAmount;
    private final BigDecimal remainingAmount;
    private final boolean isPaid;
}
