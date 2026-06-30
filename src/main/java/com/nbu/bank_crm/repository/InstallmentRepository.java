package com.nbu.bank_crm.repository;

import com.nbu.bank_crm.entity.Client;
import com.nbu.bank_crm.entity.Installment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstallmentRepository  extends JpaRepository<Installment, Long> {
    List<Installment> findByLoan_IdOrderByMonth(long loanId);
}
