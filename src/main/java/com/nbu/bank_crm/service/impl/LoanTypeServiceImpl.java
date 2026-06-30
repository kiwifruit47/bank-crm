package com.nbu.bank_crm.service.impl;

import com.nbu.bank_crm.entity.LoanType;
import com.nbu.bank_crm.repository.LoanTypeRepository;
import com.nbu.bank_crm.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanTypeServiceImpl implements LoanTypeService {
    private final LoanTypeRepository loanTypeRepository;

    @Override
    public List<LoanType> getAll() {
        return loanTypeRepository.findAll();
    }
}
