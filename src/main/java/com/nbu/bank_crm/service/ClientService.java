package com.nbu.bank_crm.service;

import com.nbu.bank_crm.entity.Client;

public interface ClientService {
    Client create(Client client);
    Client getById(long id);
}
