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
import java.util.Random;
import java.util.stream.Collectors;

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

    @Override
    public String generateIban() {
        String IBAN;
        do {
            String numbers = new Random()
                    .ints(20, 0, 10)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining());
            IBAN = "BG" + numbers;
        } while (accountRepository.findByIBAN(IBAN).isPresent());
        return IBAN;
    }

    @Transactional
    @Override
    public Account closeAccount(long id) {
        Account account = getById(id);
        account.setActive(false);
        return accountRepository.save(account);
    }
}
