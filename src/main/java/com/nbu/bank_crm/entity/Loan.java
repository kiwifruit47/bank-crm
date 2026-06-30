package com.nbu.bank_crm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "loans")
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Getter
public class Loan extends BaseEntity{
    @Positive
    private final BigDecimal amount;
    @Positive
    private final int installmentsCount;
    private final LocalDate conclusionDate = LocalDate.now();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private final Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_type_id")
    private final LoanType loanType;
    @OneToMany(mappedBy = "loan", fetch = FetchType.LAZY)
    private Set<Installment> installments;
}
