package com.nbu.bank_crm.service.impl;

import com.nbu.bank_crm.entity.Account;
import com.nbu.bank_crm.entity.Client;
import com.nbu.bank_crm.exception.NotFoundException;
import com.nbu.bank_crm.repository.AccountRepository;
import com.nbu.bank_crm.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public Account create(Account account, Client client) {
        account.setClient(client);
        return accountRepository.save(account);
    }

    @Override
    public Account getById(long id) {
        Optional<Account> optional = accountRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException(Account.class, id);
        }
        return optional.get();
    }

    @Override
    public Account getByIBAN(String IBAN) {
        Optional<Account> optional = accountRepository.findByIBAN(IBAN);
        if (optional.isEmpty()) {
            throw new NotFoundException(Account.class, IBAN);
        }
        return optional.get();
    }
}
