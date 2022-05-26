package com.example.hostpitalapp.repositories;

import com.example.hostpitalapp.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
