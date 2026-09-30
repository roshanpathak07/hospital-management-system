package com.hospital.management.hospitalmanagementsystem.services;

import com.hospital.management.hospitalmanagementsystem.models.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> getAllPatients();
    Patient createPatient(Patient patient);
    Patient getPatientById(Long id);
    String deletePatient(Long id);
    String updatePatient(Long id, Patient patient);
}
