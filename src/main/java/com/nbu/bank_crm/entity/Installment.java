package com.nbu.bank_crm.entity;

import com.nbu.bank_crm.util.YearMonthConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.YearMonth;

@Entity
@Table(name = "installments")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Installment extends BaseEntity {
    @Convert(converter = YearMonthConverter.class)
    private final YearMonth month = YearMonth.now();
    @Positive
    private final BigDecimal amount;
    @Positive
    @Column(name = "principal_amount")
    private final BigDecimal principalAmount;
    @Positive
    @Column(name = "interest_amount")
    private final BigDecimal interestAmount;
    @PositiveOrZero
    @Column(name = "remaining_amount")
    private final BigDecimal remainingAmount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id")
    private final Loan loan;
    @Column(name = "is_paid")
    private boolean isPaid = false;
}
