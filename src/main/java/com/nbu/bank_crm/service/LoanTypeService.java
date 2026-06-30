package com.nbu.bank_crm.service;

import com.nbu.bank_crm.entity.LoanType;
import com.nbu.bank_crm.repository.LoanTypeRepository;

import java.util.List;

public interface LoanTypeService {
    List<LoanType> getAll();
    LoanType getByName(String name);
}
