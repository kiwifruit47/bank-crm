package com.nbu.bank_crm.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class CreateNaturalPersonAccountRequest {
    @NotBlank
    private final String PIN;
    private final BigDecimal deposit = BigDecimal.ZERO;
}
