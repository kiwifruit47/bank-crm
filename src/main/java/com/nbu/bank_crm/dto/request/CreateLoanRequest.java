package com.nbu.bank_crm.dto.request;

import com.nbu.bank_crm.entity.Account;
import com.nbu.bank_crm.entity.Installment;
import com.nbu.bank_crm.entity.LoanType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class CreateLoanRequest {
    @Positive
    private final BigDecimal amount;
    @Positive
    private final int installmentsCount;
    @NotBlank
    private final String IBAN;
    private final String loanType;
}
