package com.hospital.management.hospitalmanagementsystem.services;

import com.hospital.management.hospitalmanagementsystem.exceptions.PatientNotFoundException;
import com.hospital.management.hospitalmanagementsystem.models.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new PatientNotFoundException(
                                "Patient with ID " + id + " not found"
                        ));
        return patient;
    }

    @Override
    public String deletePatient(Long id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new PatientNotFoundException(
                                "Patient with ID " + id + " not found"
                        ));

        patientRepository.delete(patient);

        return "Patient Deleted Successfully";

    }

    @Override
    public String updatePatient(Long id, Patient patient) {

        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new PatientNotFoundException(
                                "Patient with ID " + id + " not found"
                        ));
        existingPatient.setName(patient.getName());
        existingPatient.setGender(patient.getGender());
        existingPatient.setAge(patient.getAge());

        patientRepository.save(existingPatient);

        return "Patient with ID " + id + " updated successfully";
    }
}
