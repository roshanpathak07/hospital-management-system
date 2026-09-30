package com.hospital.management.hospitalmanagementsystem.controllers;

import com.hospital.management.hospitalmanagementsystem.models.Patient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatient();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient patient1 = patientService.createPatient(patient);
        return new ResponseEntity<>(patient1, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if(patient != null) {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else{
            String message = "Patient with ID " + id + " not found";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if(patient != null) {
            patientService.deletePatient(id);
            String message = "Patient Deleted Successfully";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else{
            String message = "Patient with ID " + id + " not found";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePatient(
            @PathVariable Long id,
            @RequestBody Patient patient) {

        Patient existingPatient = patientService.getPatientById(id);

        if (existingPatient != null) {
            patientService.updatePatient(id, patient);
            String message = "Patient Updated Successfully";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            String message = "Patient with ID " + id + " not found";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

}
