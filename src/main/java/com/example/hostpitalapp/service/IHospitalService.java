package com.example.hostpitalapp.service;

import com.example.hostpitalapp.entities.Appointment;
import com.example.hostpitalapp.entities.Consultation;
import com.example.hostpitalapp.entities.Doctor;
import com.example.hostpitalapp.entities.Patient;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Patient findPatientByName(String name);
    Doctor saveDoctor(Doctor doctor);
    Doctor findDoctorByName(String name);
    Appointment saveAppointment(Appointment appointment);
    Appointment findAppointmentById(Long id);
    Consultation saveConsultation(Consultation consultation);
}
