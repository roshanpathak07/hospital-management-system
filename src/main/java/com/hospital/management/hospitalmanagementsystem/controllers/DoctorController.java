package com.hospital.management.hospitalmanagementsystem.controllers;

import com.hospital.management.hospitalmanagementsystem.models.Doctor;
import com.hospital.management.hospitalmanagementsystem.models.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor doctor1 = doctorService.createDoctor(doctor);
        return new ResponseEntity<>(doctor1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);

        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    "Doctor with ID " + id + " not found",
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        if(doctor != null) {
            doctorService.deleteDoctor(id);
            String message = "Doctor Deleted Successfully";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else{
            String message = "Doctor with ID " + id + " not found";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDoctor(
            @PathVariable Long id,
            @RequestBody Doctor doctor) {

        Doctor existingDoctor = doctorService.getDoctorById(id);

        if (existingDoctor != null) {
            doctorService.updateDoctor(id, doctor);
            String message = "Doctor Updated Successfully";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            String message = "Doctor with ID " + id + " not found";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
