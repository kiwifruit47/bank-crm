package com.nbu.bank_crm.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateLegalEntityAccountRequest {
    @NotBlank
    private final String UIC;
    private final BigDecimal deposit = BigDecimal.ZERO;
}
