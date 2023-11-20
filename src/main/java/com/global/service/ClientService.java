package com.global.service;

import com.global.entity.Client;
import com.global.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;

    public List<Client> findAll(){

        return clientRepo.findAll();
    }
    public List<Client> saveAll(List<Client> clients){

        return clientRepo.saveAll(clients);
    }
}
