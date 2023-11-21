package com.global.service;

import com.global.entity.AppUser;
import com.global.entity.Owner;
import com.global.error.CustomException;
import com.global.repository.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepo ownerRepo;

    @Autowired
    private UserService userService;


    public List<Owner> findAll(){
        return ownerRepo.findAll();
    }

    public List<Owner> saveAll(List<Owner> carOwners) {

        return ownerRepo.saveAll(carOwners);
    }

    public Owner findById (Long id)
    {
        return ownerRepo.findById(id).orElseThrow(() -> new CustomException("This Owner does not exist"));
    }

    public Owner findOwnerByName(String name) {
        Owner carOwner = ownerRepo.findByName(name);
        if(carOwner == null )
        {
            throw new CustomException("This Owner does not exist");
        }
        return carOwner;
    }

    public Owner findOwnerByDrivingLicence(String drivingLicence) {
        return  ownerRepo.findByDrivingLicence(drivingLicence);

    }
@Transactional
    public Owner createOwner(Owner carOwner) {

        if(findOwnerByDrivingLicence(carOwner.getDrivingLicence()) != null ){
            throw new CustomException("This Owner already exists");
        }
        Owner carOwner1 = new Owner();
        carOwner1.setName(carOwner.getName());
        carOwner1.setAge(carOwner.getAge());
        carOwner1.setPhoneNumber(carOwner.getPhoneNumber());
        carOwner1.setDrivingLicence(carOwner.getDrivingLicence());

        AppUser appUser = userService.findById(carOwner.getAppUser().getId());
        findOwnerByUserId(carOwner.getAppUser().getId());
        carOwner1.setAppUser(appUser);

        return ownerRepo.save(carOwner1);

    }

    private void findOwnerByUserId(Long id) {
        if(ownerRepo.findByAppUserId(id) != null) {
            throw new CustomException("This user Id already exists");
        }


    }

    public String deleteOwnerById(Long id) {

        findById(id);
        ownerRepo.deleteById(id);
        return "Deleted" ;
    }
}
