package com.nbu.bank_crm.dto.request;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class CreatePaymentPlanPreviewRequest {
    @Positive
    private final BigDecimal amount;
    @Positive
    private final int installmentsCount;
    private final String loanType;
}
