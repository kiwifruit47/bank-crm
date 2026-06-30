package com.nbu.bank_crm.service.impl;

import com.nbu.bank_crm.entity.*;
import com.nbu.bank_crm.exception.AmountExceedingMaxException;
import com.nbu.bank_crm.exception.InstallmentCountExceedingMaxException;
import com.nbu.bank_crm.exception.NotFoundException;
import com.nbu.bank_crm.repository.LoanRepository;
import com.nbu.bank_crm.service.InstallmentService;
import com.nbu.bank_crm.service.LoanService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final InstallmentService installmentService;

    @Transactional
    @Override
    public Loan create(Account account, LoanType loanType, BigDecimal amount, int installmentCount) {
       if (amount.compareTo(loanType.getMaxAmount()) > 0) {
           throw new AmountExceedingMaxException(loanType.getMaxAmount());
       }

       if (installmentCount > loanType.getMaxInstallmentCount()) {
           throw new InstallmentCountExceedingMaxException(loanType.getMaxInstallmentCount());
       }

       Loan loan = new Loan(amount, installmentCount, account, loanType);
       loan = loanRepository.save(loan);
       installmentService.createPaymentPlan(loan);

       return loan;
    }

    @Override
    public List<Loan> getByAccountId(long id) {
        return loanRepository.findAllByAccount_Id(id);
    }

    @Override
    public Loan getById(long id) {
        Optional<Loan> optional = loanRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException(Loan.class, id);
        }
        return optional.get();
    }

    @Transactional
    @Override
    public boolean isPaidOff(long id) {
        List<Installment> installments = installmentService.getAllByLoanId(id);
        return installments.stream().allMatch(Installment::isPaid);
    }
}
