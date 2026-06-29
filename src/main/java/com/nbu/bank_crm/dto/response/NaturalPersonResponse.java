package com.nbu.bank_crm.dto.response;

import lombok.Builder;


@Builder
public class NaturalPersonResponse {
    private final String PIN;
    private final String firstName;
    private final String lastName;
}
