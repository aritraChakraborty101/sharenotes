package com.aritra.sharenotes.dto;

import com.aritra.sharenotes.entity.Role;
import java.util.Set;

public record RoleUpdateRequest(Set<Role> newRoles) {}
