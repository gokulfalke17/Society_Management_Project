package com.society.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.society.entity.Admin;
import com.society.repository.IAdminRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final IAdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(IAdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        String defaultEmail = "admin@society.com";
        String defaultRole = "ADMIN";

        Admin existingAdmin = adminRepository.adminLogin(defaultEmail, defaultRole);

        if (existingAdmin == null) {
            String password = passwordEncoder.encode("admin123");
            Admin admin = new Admin(null, defaultEmail, password, defaultRole);
            adminRepository.save(admin);
            System.out.println("Default admin created.");
        } else {
            System.out.println("Default admin already exists :: "+ defaultEmail);
        }
    }
}
