package com.nbu.bank_crm.service;

import com.nbu.bank_crm.entity.Account;
import com.nbu.bank_crm.entity.Client;

public interface AccountService {
    Account create(Account account, Client client);
    Account getById(long id);
    Account getByIBAN(String IBAN);
}
