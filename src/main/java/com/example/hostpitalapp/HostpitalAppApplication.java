package com.example.hostpitalapp;

import com.example.hostpitalapp.Security.entities.AppUser;
import com.example.hostpitalapp.Security.service.SecurityService;
import com.example.hostpitalapp.entities.*;
import com.example.hostpitalapp.repositories.AppointmentRepository;
import com.example.hostpitalapp.repositories.ConsultationRepository;
import com.example.hostpitalapp.repositories.DoctorRepository;
import com.example.hostpitalapp.repositories.PatientRepository;
import com.example.hostpitalapp.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HostpitalAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(HostpitalAppApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@Bean //Comment this so it doesnt execute
    CommandLineRunner start(IHospitalService hospitalService){
        return args -> {
            //Add patients
            Stream.of("Mohammed", "Hiba", "Aymane", "Imad").forEach(name->{
                Patient patient = new Patient();
                patient.setDateOfBirth(new Date());
                patient.setName(name);
                patient.setSick(Math.random()<0.5?true:false);
                patient.setScore((int)(Math.random()*100));
                hospitalService.savePatient(patient);
            });
            /*
            //Add doctors
            Stream.of("Naoufal", "Mehdi", "Khalid", "Brahim").forEach(name->{
                Doctor doctor = new Doctor();
                doctor.setName(name);
                doctor.setEmail(name+"@gmail.com");
                doctor.setSpeciality(Math.random()<0.5?"Dentist":"Cardio");
                hospitalService.saveDoctor(doctor);
            });
            //Get patient and doctor
            Patient patient = hospitalService.findPatientByName("Mohammed");
            Doctor doctor = hospitalService.findDoctorByName("Naoufal");
            //Add appointment
            Appointment appointment = new Appointment();
            appointment.setDate(new Date());
            appointment.setDoctor(doctor);
            appointment.setStatus(AppointmentStatus.PENDING);
            appointment.setPatient(patient);
            hospitalService.saveAppointment(appointment);
            //Get appointment
            Appointment appointment1 = hospitalService.findAppointmentById(1L);
            //Add consultation
            Consultation consultation = new Consultation();
            consultation.setAppointment(appointment1);
            consultation.setConsultationDate(new Date());
            consultation.setReport("Consultation report....");
            hospitalService.saveConsultation(consultation);*/
        };
    }

    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("user1","1234","1234");
            securityService.saveNewUser("admin","1234","1234");

            securityService.saveNewRole("USER");
            securityService.saveNewRole("ADMIN");

            securityService.addRoleTouser("user1","USER");
            securityService.addRoleTouser("admin","USER");
            securityService.addRoleTouser("admin","ADMIN");
        };
    }
}
