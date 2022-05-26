package com.example.hostpitalapp.service;

import com.example.hostpitalapp.entities.Appointment;
import com.example.hostpitalapp.entities.Consultation;
import com.example.hostpitalapp.entities.Doctor;
import com.example.hostpitalapp.entities.Patient;
import com.example.hostpitalapp.repositories.AppointmentRepository;
import com.example.hostpitalapp.repositories.ConsultationRepository;
import com.example.hostpitalapp.repositories.DoctorRepository;
import com.example.hostpitalapp.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class HospitalServiceImpl implements IHospitalService {
    private AppointmentRepository appointmentRepository;
    private ConsultationRepository consultationRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient findPatientByName(String name) {
        return patientRepository.findByName(name);
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor findDoctorByName(String name) {
        return doctorRepository.findByName(name);
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment findAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
}
