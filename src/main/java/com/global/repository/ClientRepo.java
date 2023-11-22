package com.global.repository;

import com.global.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClientRepo extends JpaRepository<Client,Long> {


     Client findByUserId(Long id) ;

    Client findByPhoneNumber(String phoneNumber);


}
