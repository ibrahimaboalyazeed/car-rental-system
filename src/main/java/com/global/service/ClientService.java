package com.global.service;

import com.global.entity.AppUser;
import com.global.entity.Client;
import com.global.error.CustomException;
import com.global.repository.ClientRepo;
import com.global.security.AppUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private UserService userService;

    public List<Client> findAll() {

        return clientRepo.findAll();
    }

    public List<Client> saveAll(List<Client> clients) {

        return clientRepo.saveAll(clients);
    }

    public Client findById(Long id) {
        return clientRepo.findById(id).orElseThrow(() -> new CustomException("This Client is not found"));
    }
    public Client findByUserId(Long id) {
        Client client =  clientRepo.findByUserId(id);
        if(client == null){
            throw new CustomException("This Client is not found");
        }
        return client;
    }

    public String deleteById(Long id) {
        findById(id);
        clientRepo.deleteById(id);
        return "Deleted";
    }

    public Client insertClient(Client client) {

        if (isPhoneNumberExists(client.getPhoneNumber())) {
            throw new CustomException("This phone number already exists");
        }
        if (isUserExists(client.getUser().getId())) {
            throw new CustomException("This User already exists");
        }
        Client clientToInsert = new Client();
        clientToInsert.setUser(userService.findById(client.getUser().getId()));
        clientToInsert.setCountry(client.getCountry());
        clientToInsert.setCity(client.getCity());
        clientToInsert.setStreet(client.getStreet());
        clientToInsert.setPhoneNumber(client.getPhoneNumber());
        clientToInsert.setFullName(client.getFullName());

        return clientRepo.save(clientToInsert);

    }

    public boolean isPhoneNumberExists(String phoneNumber) {

        if (clientRepo.findByPhoneNumber(phoneNumber) != null) {
            return true;
        }
        return false;
    }

    public boolean isUserExists(Long id) {

        if (clientRepo.findByUserId(id) != null) {
            return true;
        }
        return false;
    }

    public Client updatePhoneNumber(Long id, String phoneNumber) {

        Client client = new Client();
        client = clientRepo.findById(id).orElseThrow(() -> new CustomException("This Client is not found"));
        client.setPhoneNumber(phoneNumber);
        return clientRepo.save(client);
    }
    public boolean isOwner(Authentication authentication, Long id) {

        AppUserDetail userDetails = (AppUserDetail) authentication.getPrincipal();
        Long authenticatedUserId = userDetails.getId();
        System.out.println("...............>>>  "+authenticatedUserId+"   <<<<<<<<<<<<<...................");
        Client client =findById(id);
        Long userId = client.getUser().getId();
        System.out.println("...............>>>  "+userId+"   <<<<<<<<<<<<<...................");
        return authenticatedUserId.equals(userId);
    }

    public boolean isOwnerUsingUserId(Authentication authentication, Long userId) {

        AppUserDetail userDetails = (AppUserDetail) authentication.getPrincipal();
        Long authenticatedUserId = userDetails.getId();
        System.out.println("...............>>>  "+authenticatedUserId+"   <<<<<<<<<<<<<...................");
        System.out.println("...............>>>  "+userId+"   <<<<<<<<<<<<<...................");
        return authenticatedUserId.equals(userId);
    }
}
