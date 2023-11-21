package com.global.repository;

import com.global.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepo extends JpaRepository<Owner,Long> {
    Owner findByName(String name);

    Owner findByDrivingLicence(String drivingLicence);

    Owner findByAppUserId(Long id);
}
