package com.nbu.bank_crm.service;

import com.nbu.bank_crm.entity.Account;
import com.nbu.bank_crm.entity.Loan;
import com.nbu.bank_crm.entity.LoanType;

import java.math.BigDecimal;
import java.util.List;

public interface LoanService {
    Loan create(Account account, LoanType loanType, BigDecimal amount, int installmentCount);
    List<Loan> getByAccountId(long id);
    Loan getById(long id);
    boolean isPaidOff(long id);
}
