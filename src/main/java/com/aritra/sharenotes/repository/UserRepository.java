package com.aritra.sharenotes.repository;

import com.aritra.sharenotes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    // This allows you to find a user by their email for login/verification
    Optional<User> findByEmail(String email);
}