package com.example.hostpitalapp.Security.service;

import com.example.hostpitalapp.Security.entities.AppRole;
import com.example.hostpitalapp.Security.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username, String password, String verifyPassword);
    AppRole saveNewRole(String role);
    void addRoleTouser(String username, String role);
    void removeRoleFromuser(String username, String role);
    AppUser loadUserByUsername(String username);
}
