package com.nbu.bank_crm.repository;

import com.nbu.bank_crm.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
