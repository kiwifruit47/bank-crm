package com.nbu.bank_crm.service;

import com.nbu.bank_crm.entity.NaturalPerson;

public interface NaturalPersonService {
    NaturalPerson getByPIN(String PIN);
}
