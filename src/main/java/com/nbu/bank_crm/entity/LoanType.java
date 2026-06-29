package com.nbu.bank_crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "loan_types")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class LoanType extends BaseEntity {
    private final String name;
    private final BigDecimal interest;
    private final BigDecimal maxAmount;
    private final int maxInstallmentCount;
}
