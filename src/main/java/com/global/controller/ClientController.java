package com.global.controller;

import com.global.entity.Client;
import com.global.error.CustomResponse;
import com.global.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/clients")
@Validated
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(new CustomResponse(clientService.findAll()));
    }
    @GetMapping("/{id}")
    @PreAuthorize("@clientService.isOwner(authentication, #id)")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(new CustomResponse(clientService.findById(id)));
    }
    @GetMapping("/user/{id}")
    @PreAuthorize("@clientService.isOwnerUsingUserId(authentication, #id)")
    public ResponseEntity<?> findByUserId(@PathVariable Long id){
        return ResponseEntity.ok(new CustomResponse(clientService.findByUserId(id)));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("@clientService.isOwner(authentication, #id)")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(new CustomResponse(clientService.deleteById(id)));
    }
    @PostMapping("/create")
    public ResponseEntity<?> createClient (@RequestBody Client client)
    {
        return  ResponseEntity.ok(new CustomResponse(clientService.insertClient(client)));
    }
    @PutMapping("/update-phoneNumber")
    @PreAuthorize("@clientService.isOwner(authentication, #id)")
    public ResponseEntity<?> updatePhoneNumber (@RequestParam Long id ,@RequestParam String phoneNumber)
    {
        return  ResponseEntity.ok(new CustomResponse(clientService.updatePhoneNumber(id,phoneNumber)));
    }
}
