package com.eid.nursing_backend.security;

import com.eid.nursing_backend.model.Role;
import com.eid.nursing_backend.model.User;
import com.eid.nursing_backend.repository.RoleRepository;
import com.eid.nursing_backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder encoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        for (Role.RoleName roleName : Role.RoleName.values()) {
            if (roleRepository.findByName(roleName).isEmpty()) {
                Role role = new Role();
                role.setName(roleName);
                roleRepository.save(role);
            }
        }

        if (userRepository.findByEmail("admin@system.com").isEmpty()) {
            Role adminRole = roleRepository.findByName(Role.RoleName.ADMIN).orElseThrow();

            User admin = new User();
            admin.setFullName("Main Admin");
            admin.setEmail("admin@system.com");
            admin.setPassword(encoder.encode("admin123"));
            admin.setRoles(Set.of(adminRole));

            userRepository.save(admin);
        }
    }
}