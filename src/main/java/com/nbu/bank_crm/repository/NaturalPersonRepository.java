package com.nbu.bank_crm.repository;

import com.nbu.bank_crm.entity.Client;
import com.nbu.bank_crm.entity.NaturalPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NaturalPersonRepository  extends JpaRepository<NaturalPerson, Long> {
    Optional<NaturalPerson> findByPIN(String PIN);
}
