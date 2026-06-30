package com.nbu.bank_crm.repository;

import com.nbu.bank_crm.entity.Client;
import com.nbu.bank_crm.entity.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTypeRepository  extends JpaRepository<LoanType, Long> {
}
