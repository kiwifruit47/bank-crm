package com.nbu.bank_crm.repository;

import com.nbu.bank_crm.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository  extends JpaRepository<Account, Long> {
    Optional<Account> findByIBAN(String IBAN);
}
