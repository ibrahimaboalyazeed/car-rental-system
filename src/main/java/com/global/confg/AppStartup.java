package com.global.confg;

import com.global.entity.Role;
import com.global.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class AppStartup implements CommandLineRunner {


    private final RoleService roleService;


    @Override
    public void run(String... args) throws Exception {

        if (roleService.findAll().isEmpty()) {
            Role role = new Role();
            role.setName("ROLE_ADMIN");

            Role role1 = new Role();
            role1.setName("ROLE_USER");

            roleService.insertAll(Arrays.asList(role, role1));

        }

    }
}
