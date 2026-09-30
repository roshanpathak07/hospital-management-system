package com.hospital.management.hospitalmanagementsystem.controllers;

import com.hospital.management.hospitalmanagementsystem.models.Patient;
import com.hospital.management.hospitalmanagementsystem.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
         return new ResponseEntity<>(
                patientService.getAllPatients(),
                HttpStatus.OK
        );
    }


    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient patient1 = patientService.createPatient(patient);
        return new ResponseEntity<>(patient1, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
            return new ResponseEntity<>(patient, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
            String message =patientService.deletePatient(id);
            return new ResponseEntity<>(message, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePatient(
            @PathVariable Long id,
            @RequestBody Patient patient) {

            String message = patientService.updatePatient(id, patient);
            return new ResponseEntity<>(message, HttpStatus.OK);

    }

}
