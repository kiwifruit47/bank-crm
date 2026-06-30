package com.nbu.bank_crm.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class Account extends BaseEntity {
    private final String IBAN;
    @Setter
    private BigDecimal balance = BigDecimal.ZERO;
    @Setter
    @Column(name = "is_active")
    private boolean isActive = true;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @Setter
    private Client client;

}
