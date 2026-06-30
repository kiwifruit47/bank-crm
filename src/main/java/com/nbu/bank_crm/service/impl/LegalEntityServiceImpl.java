package com.nbu.bank_crm.service.impl;

import com.nbu.bank_crm.entity.Client;
import com.nbu.bank_crm.entity.LegalEntity;
import com.nbu.bank_crm.entity.Loan;
import com.nbu.bank_crm.exception.NotFoundException;
import com.nbu.bank_crm.repository.LegalEntityRepository;
import com.nbu.bank_crm.service.LegalEntityService;
import com.nbu.bank_crm.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LegalEntityServiceImpl implements LegalEntityService {
    private final LegalEntityRepository legalEntityRepository;

    @Override
    public LegalEntity getByUIC(String UIC) {
        Optional<LegalEntity> optional = legalEntityRepository.findByUIC(UIC);
        if (optional.isEmpty()) {
            throw new NotFoundException(Client.class, UIC);
        }
        return optional.get();
    }
}
