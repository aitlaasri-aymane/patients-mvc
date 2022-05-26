package com.example.hostpitalapp.repositories;

import com.example.hostpitalapp.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByName(String name);
}
