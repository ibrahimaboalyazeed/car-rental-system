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

    public List<Owner> saveAll(List<Owner> owners) {

        return ownerRepo.saveAll(owners);
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
    public Owner createOwner(Owner owner) {

        if(findOwnerByDrivingLicence(owner.getDrivingLicence()) != null ){
            throw new CustomException("This Owner already exists");
        }
        Owner owner1 = new Owner();
        owner1.setName(owner.getName());
        owner1.setAge(owner.getAge());
        owner1.setPhoneNumber(owner.getPhoneNumber());
        owner1.setDrivingLicence(owner.getDrivingLicence());

        AppUser appUser = userService.findById(owner.getAppUser().getId());
        findOwnerByUserId(owner.getAppUser().getId());
        owner1.setAppUser(appUser);

        return ownerRepo.save(owner1);

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
