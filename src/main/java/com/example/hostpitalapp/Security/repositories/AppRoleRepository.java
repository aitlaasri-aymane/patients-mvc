package com.example.hostpitalapp.Security.repositories;

import com.example.hostpitalapp.Security.entities.AppRole;
import com.example.hostpitalapp.Security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {
    AppRole findByRole(String role);
}
