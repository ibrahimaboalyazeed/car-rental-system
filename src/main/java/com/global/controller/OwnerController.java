package com.global.controller;

import com.global.entity.Owner;
import com.global.error.CustomResponse;
import com.global.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/all")
    public ResponseEntity<?> findAllOwners()
    {
        return ResponseEntity.ok(new CustomResponse(ownerService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOwnerById(@PathVariable Long id){
        return ResponseEntity.ok(new CustomResponse(ownerService.findById(id)));
    }

    @GetMapping("/name")
    public ResponseEntity<?> findOwnerByName(@RequestParam String name){
        return ResponseEntity.ok(new CustomResponse(ownerService.findOwnerByName(name)));
    }
    @PostMapping("/create")
    public ResponseEntity<?> createOwner (@RequestBody Owner owner){
        return ResponseEntity.ok( new CustomResponse(ownerService.createOwner(owner)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOwnerById(@PathVariable Long id){
        return ResponseEntity.ok(new CustomResponse(ownerService.deleteOwnerById(id)));
    }
}
