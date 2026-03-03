package com.aritra.sharenotes.controller;

import com.aritra.sharenotes.dto.RoleUpdateRequest;
import com.aritra.sharenotes.entity.User;
import com.aritra.sharenotes.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/users")
public class AdminController {

    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PutMapping("/{id}/roles")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public ResponseEntity<?> updateRoles(@PathVariable UUID id,
                                         @RequestBody RoleUpdateRequest request) {
        if (request.newRoles() == null || request.newRoles().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "newRoles must not be empty");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        user.setRoles(request.newRoles());
        userRepository.save(user);

        return ResponseEntity.ok(Map.of(
                "message", "Roles updated successfully",
                "userId", user.getId().toString(),
                "username", user.getUsername(),
                "roles", user.getRoles().stream().map(Enum::name).collect(Collectors.toSet())
        ));
    }
}
