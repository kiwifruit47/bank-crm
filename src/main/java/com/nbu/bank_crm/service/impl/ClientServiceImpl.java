package com.nbu.bank_crm.service.impl;

import com.nbu.bank_crm.entity.Client;
import com.nbu.bank_crm.exception.NotFoundException;
import com.nbu.bank_crm.repository.ClientRepository;
import com.nbu.bank_crm.service.ClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Transactional
    @Override
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client getById(long id) {
        Optional<Client> optional = clientRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException(Client.class, id);
        }
        return optional.get();
    }
}
