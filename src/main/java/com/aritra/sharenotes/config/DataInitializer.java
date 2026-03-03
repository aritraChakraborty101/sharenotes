package com.aritra.sharenotes.config;

import com.aritra.sharenotes.entity.Role;
import com.aritra.sharenotes.entity.User;
import com.aritra.sharenotes.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.existsByUsername("superadmin")) {
            return;
        }

        User superAdmin = User.builder()
                .username("superadmin")
                .email("superadmin@sharenotes.local")
                .password(passwordEncoder.encode("Admin@123"))
                .roles(Set.of(Role.ROLE_SUPERADMIN))
                .build();

        userRepository.save(superAdmin);
        log.warn("================================================================");
        log.warn("  Default SUPERADMIN account created.");
        log.warn("  Username : superadmin");
        log.warn("  Password : Admin@123");
        log.warn("  !! CHANGE THIS PASSWORD IMMEDIATELY IN PRODUCTION !!");
        log.warn("================================================================");
    }
}
