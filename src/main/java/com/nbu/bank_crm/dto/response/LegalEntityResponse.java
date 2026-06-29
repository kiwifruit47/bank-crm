package com.nbu.bank_crm.dto.response;

import lombok.Builder;

@Builder
public class LegalEntityResponse {
    private final String UIC;
    private final String firstName;
    private final String lastName;
    private final String legalEntityName;
}
