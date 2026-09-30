package com.hospital.management.hospitalmanagementsystem.services;

import com.hospital.management.hospitalmanagementsystem.models.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    Doctor createDoctor(Doctor doctor);
    Doctor getDoctorById(Long id);
    String deleteDoctor(Long id);
    String updateDoctor(Long id, Doctor doctor);
}
