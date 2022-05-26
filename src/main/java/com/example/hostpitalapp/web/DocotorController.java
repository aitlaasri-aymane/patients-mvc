package com.example.hostpitalapp.web;

import com.example.hostpitalapp.entities.Doctor;
import com.example.hostpitalapp.repositories.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DocotorController {
    private DoctorRepository doctorRepository;
    @GetMapping("/doctors")
    public List<Doctor> findAllDocs(){
        return doctorRepository.findAll();
    }
}
