package com.global.confg;

import com.global.entity.*;
import com.global.entity.enums.Transmission;
import com.global.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class AppStartup implements CommandLineRunner {


    private final RoleService roleService;

    private final UserService userService;

    private final ClientService clientService;

    private final OwnerService ownerService;

    private  final PasswordEncoder passwordEncoder;

    private final CategoryService categoryService;

    private final CarService carService;


    @Override
    public void run(String... args) throws Exception {

        if (roleService.findAll().isEmpty()) {
            Role role = new Role();
            role.setName("ROLE_ADMIN");
            Role role1 = new Role();
            role1.setName("ROLE_USER");

            roleService.insertAll(Arrays.asList(role, role1));

        }

        if (userService.findAll().isEmpty()) {

            // Create demo roles
            Role adminRole = roleService.findByName("ROLE_ADMIN");
            Role userRole = roleService.findByName("ROLE_USER");

            // Create demo AppUsers with roles
            AppUser appUser = new AppUser();
            appUser.setEmail("osama@gmail.com");
            appUser.setPassword(passwordEncoder.encode("osama"));
            appUser.setEnabled(true);
            appUser.addRole(userRole);

            AppUser appUser1 = new AppUser();
            appUser1.setEmail("adel@gmail.com");
            appUser1.setPassword(passwordEncoder.encode("adel"));
            appUser1.setEnabled(true);
            appUser1.addRole(userRole);

            AppUser appUser2 = new AppUser();
            appUser2.setEmail("saeed@gmail.com");
            appUser2.setPassword(passwordEncoder.encode("saeed"));
            appUser2.setEnabled(true);
            appUser2.addRole(userRole);

            AppUser appUser3 = new AppUser();
            appUser3.setEmail("kareem@gmail.com");
            appUser3.setPassword(passwordEncoder.encode("kareem"));
            appUser3.setEnabled(true);
            appUser3.addRole(userRole);

            AppUser appUser4 = new AppUser();
            appUser4.setEmail("saad@gmail.com");
            appUser4.setPassword(passwordEncoder.encode("saad"));
            appUser4.setEnabled(true);
            appUser4.addRole(userRole);

            AppUser appUser5 = new AppUser();
            appUser5.setEmail("basem@gmail.com");
            appUser5.setPassword(passwordEncoder.encode("basem"));
            appUser5.setEnabled(true);
            appUser5.addRole(userRole);

            AppUser appUser6 = new AppUser();
            appUser6.setEmail("samir@gmail.com");
            appUser6.setPassword(passwordEncoder.encode("samir"));
            appUser6.setEnabled(true);
            appUser6.addRole(userRole);

            AppUser appUser7 = new AppUser();
            appUser7.setEmail("akram@gmail.com");
            appUser7.setPassword(passwordEncoder.encode("akram"));
            appUser7.setEnabled(true);
            appUser7.addRole(userRole);

            AppUser appUser8 = new AppUser();
            appUser8.setEmail("ramy@gmail.com");
            appUser8.setPassword(passwordEncoder.encode("ramy"));
            appUser8.setEnabled(true);
            appUser8.addRole(userRole);

            AppUser appUser9 = new AppUser();
            appUser9.setEmail("yousef@gmail.com");
            appUser9.setPassword(passwordEncoder.encode("yousef"));
            appUser9.setEnabled(true);
            appUser9.addRole(userRole);

            AppUser appUser10 = new AppUser();
            appUser10.setEmail("mohamed@gmail.com");
            appUser10.setPassword(passwordEncoder.encode("mohamed"));
            appUser10.setEnabled(true);
            appUser10.addRole(userRole);

            AppUser appUser11 = new AppUser();
            appUser11.setEmail("ahmed@gmail.com");
            appUser11.setPassword(passwordEncoder.encode("ahemd"));
            appUser11.setEnabled(true);
            appUser11.addRole(userRole);

            AppUser appUser12 = new AppUser();
            appUser12.setEmail("hema@gmail.com");
            appUser12.setPassword(passwordEncoder.encode("hema"));
            appUser12.setEnabled(true);
            appUser12.addRole(userRole);

            AppUser appUser13 = new AppUser();
            appUser13.setEmail("amr@gmail.com");
            appUser13.setPassword(passwordEncoder.encode("amr"));
            appUser13.setEnabled(true);
            appUser13.addRole(userRole);

            AppUser appUser14 = new AppUser();
            appUser14.setEmail("mahmoud@gmail.com");
            appUser14.setPassword(passwordEncoder.encode("mahmoud"));
            appUser14.setEnabled(true);
            appUser14.addRole(userRole);

            AppUser appUser15 = new AppUser();
            appUser15.setEmail("zezo@gmail.com");
            appUser15.setPassword(passwordEncoder.encode("zezo"));
            appUser15.setEnabled(true);
            appUser15.addRole(userRole);

            // Save the AppUsers
            userService.saveAll(Arrays.asList(appUser12, appUser13, appUser2, appUser3, appUser4,
                    appUser5, appUser6, appUser7, appUser8, appUser9, appUser10, appUser11, appUser, appUser1, appUser14, appUser15));
        }
        if (clientService.findAll().isEmpty()) {


            // Create some clients
            Client client1 = new Client();
            client1.setFullName("Ibrahim Abo Elyazeed");
            client1.setCity("Tala");
            client1.setCountry("Egypt");
            client1.setStreet("Toukh Dalaka");
            client1.setPhoneNumber("01028394667");
            client1.setUser(userService.findById((long) 1));

            Client client2 = new Client();
            client2.setFullName("Amr Emaish");
            client2.setCity("Tala");
            client2.setCountry("Egypt");
            client2.setStreet("Toukh Dalaka");
            client2.setPhoneNumber("01028394661");
            client2.setUser(userService.findById((long) 2));

            Client client3 = new Client();
            client3.setFullName("saeed ahmed");
            client3.setCity("Tala");
            client3.setCountry("Egypt");
            client3.setStreet("Toukh Dalaka");
            client3.setPhoneNumber("01028394662");
            client3.setUser(userService.findById((long) 3));

            clientService.saveAll(Arrays.asList(client1, client2, client3));


        }

        if (ownerService.findAll().isEmpty()) {


            // Create some owners
            Owner owner1 = new Owner();
            owner1.setName("kareem mohamed");
            owner1.setAge(25);
            owner1.setPhoneNumber("1394567825");
            owner1.setDrivingLicence("1259637852");
            owner1.setAppUser(userService.findById(4L));

            Owner owner2 = new Owner();
            owner2.setName("saad mahmoud");
            owner2.setAge(50);
            owner2.setPhoneNumber("1394568964");
            owner2.setDrivingLicence("1493281458");
            owner2.setAppUser(userService.findById(5L));

            Owner owner3 = new Owner();
            owner3.setName("basem omar");
            owner3.setAge(38);
            owner3.setPhoneNumber("1871593576");
            owner3.setDrivingLicence("75527839965");
            owner3.setAppUser(userService.findById(6L));

            ownerService.saveAll(Arrays.asList(owner1, owner2, owner3));


        }
        if (categoryService.findAllCategories().isEmpty()) {


            // Create some car categories
            Category category1 = new Category();
            category1.setName("Medium car");

            Category category2 = new Category();
            category2.setName("Small car");

            Category category3 = new Category();
            category3.setName("Large car");

            Category category4 = new Category();
            category4.setName("SUVs");

            Category category5 = new Category();
            category5.setName("Featured car");

            Category category6 = new Category();
            category6.setName("Premium car");

            categoryService.saveAll(Arrays.asList(category1, category2, category3
                                                    , category4, category5, category6));

        }

        if (carService.findAll().isEmpty())
        {
            //Add some cars
            Car car1 = new Car();
            car1.setMake("Nissan");
            car1.setModel("Sunny");
            car1.setModelYear(2020);
            car1.setCategory(categoryService.findByCategoryName("Medium car"));
            car1.setOwner(ownerService.findById(1L));
            car1.setColor("black");
            car1.setDoors(4);
            car1.setAvailable(true);
            car1.setWorkArea("cairo");
            car1.setLargeBag(3);
            car1.setSmallBag(0);
            car1.setTransmission(Transmission.Automatic);
            car1.setPricePerHour(200);
            car1.setPricePerDay(1200);
            car1.setLicence("ع ن م 1458");


            Car car2 = new Car();
            car2.setMake("Kia");
            car2.setModel("Sportage");
            car2.setModelYear(2022);
            car2.setCategory(categoryService.findByCategoryName("Medium car"));
            car2.setOwner(ownerService.findById(2L));
            car2.setColor("red");
            car2.setDoors(4);
            car2.setAvailable(true);
            car2.setWorkArea("alexandria");
            car2.setLargeBag(1);
            car2.setSmallBag(1);
            car2.setTransmission(Transmission.Automatic);
            car2.setPricePerHour(300);
            car2.setPricePerDay(1500);
            car2.setLicence("س ك ا 6842");


            Car car3 = new Car();
            car3.setMake("Mercedes");
            car3.setModel("Glc");
            car3.setModelYear(2020);
            car3.setCategory(categoryService.findByCategoryName("Premium car"));
            car3.setOwner(ownerService.findById(3L));
            car3.setColor("white");
            car3.setDoors(4);
            car3.setAvailable(false);
            car3.setWorkArea("tanta");
            car3.setLargeBag(1);
            car3.setSmallBag(1);
            car3.setTransmission(Transmission.Manual);
            car3.setPricePerHour(250);
            car3.setPricePerDay(1400);
            car3.setLicence("ش ح ك 4963");


            Car car4 = new Car();
            car4.setMake("BMW");
            car4.setModel("Z4M");
            car4.setModelYear(2024);
            car4.setCategory(categoryService.findByCategoryName("Featured car"));
            car4.setOwner(ownerService.findById(1L));
            car4.setColor("white");
            car4.setDoors(4);
            car4.setAvailable(true);
            car4.setWorkArea("cairo");
            car4.setLargeBag(1);
            car4.setSmallBag(1);
            car4.setTransmission(Transmission.Automatic);
            car4.setPricePerHour(250);
            car4.setPricePerDay(3000);
            car4.setLicence("ش ح ك 4963");


            Car car5 = new Car();
            car5.setMake("Ferrari");
            car5.setModel("Enzo");
            car5.setModelYear(2024);
            car5.setCategory(categoryService.findByCategoryName("Featured car"));
            car5.setOwner(ownerService.findById(2L));
            car5.setColor("Blue");
            car5.setDoors(4);
            car5.setAvailable(true);
            car5.setWorkArea("cairo");
            car5.setLargeBag(1);
            car5.setSmallBag(1);
            car5.setTransmission(Transmission.Automatic);
            car5.setPricePerHour(250);
            car5.setPricePerDay(2500);
            car5.setLicence("ش ح ك 4963");


            Car car6 = new Car();
            car6.setMake("Ford");
            car6.setModel("GT");
            car6.setModelYear(2024);
            car6.setCategory(categoryService.findByCategoryName("Featured car"));
            car6.setOwner(ownerService.findById(3L));
            car6.setColor("white");
            car6.setDoors(4);
            car6.setAvailable(true);
            car6.setWorkArea("cairo");
            car6.setLargeBag(1);
            car6.setSmallBag(1);
            car6.setTransmission(Transmission.Automatic);
            car6.setPricePerHour(250);
            car6.setPricePerDay(2800);
            car6.setLicence("ش ح ك 4963");


            Car car7 = new Car();
            car7.setMake("Aston");
            car7.setModel("Martin DB9");
            car7.setModelYear(2022);
            car7.setCategory(categoryService.findByCategoryName("Featured car"));
            car7.setOwner(ownerService.findById(1L));
            car7.setColor("white");
            car7.setDoors(4);
            car7.setAvailable(true);
            car7.setWorkArea("cairo");
            car7.setLargeBag(1);
            car7.setSmallBag(1);
            car7.setTransmission(Transmission.Automatic);
            car7.setPricePerHour(250);
            car7.setPricePerDay(2600);
            car7.setLicence("ش ح ك 4963");





            carService.saveAll(Arrays.asList(car1,car2,car3,car4,car5,car6,car7));


        }







    }
}
