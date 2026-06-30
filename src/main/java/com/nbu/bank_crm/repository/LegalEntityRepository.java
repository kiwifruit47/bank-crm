package com.nbu.bank_crm.repository;

import com.nbu.bank_crm.entity.LegalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LegalEntityRepository  extends JpaRepository<LegalEntity, Long> {
    Optional<LegalEntity> findByUIC(String UIC);
}
