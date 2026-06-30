package com.nbu.bank_crm.service;

import com.nbu.bank_crm.entity.LegalEntity;

public interface LegalEntityService {
    LegalEntity getByUIC(String UIC);
}
