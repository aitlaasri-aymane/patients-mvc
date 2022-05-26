package com.example.hostpitalapp.repositories;

import com.example.hostpitalapp.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByName(String name);
    Page<Patient> findByNameContains(String keyword, Pageable pageable);
 }
