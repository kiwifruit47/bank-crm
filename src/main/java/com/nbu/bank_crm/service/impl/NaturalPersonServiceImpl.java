package com.nbu.bank_crm.service.impl;

import com.nbu.bank_crm.entity.Client;
import com.nbu.bank_crm.entity.NaturalPerson;
import com.nbu.bank_crm.exception.NotFoundException;
import com.nbu.bank_crm.repository.NaturalPersonRepository;
import com.nbu.bank_crm.service.NaturalPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NaturalPersonServiceImpl implements NaturalPersonService {
    private final NaturalPersonRepository naturalPersonRepository;
    @Override
    public NaturalPerson getByPIN(String PIN) {
        Optional<NaturalPerson> optional = naturalPersonRepository.findByPIN(PIN);
        if (optional.isEmpty()) {
            throw new NotFoundException(Client.class, PIN);
        }
        return optional.get();
    }
}
