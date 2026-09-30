package com.hospital.management.hospitalmanagementsystem.controllers;

import com.hospital.management.hospitalmanagementsystem.models.Appointment;
import com.hospital.management.hospitalmanagementsystem.models.Doctor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment createdAppointment  = appointmentService.createAppointment(appointment);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);

        if (appointment != null) {
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    "Appointment with ID " + id + " not found",
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {

        Appointment appointment = appointmentService.getAppointmentById(id);

        if (appointment != null) {
            appointmentService.deleteAppointment(id);
            String message = "Appointment Deleted Successfully";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    "Appointment with ID " + id + " not found",
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAppointment(
            @PathVariable Long id,
            @RequestBody Appointment appointment) {

        Appointment existingAppointment = appointmentService.getAppointmentById(id);

        if (existingAppointment != null) {
            appointmentService.updateAppointment(id, appointment);
            String message = "Appointment Updated Successfully";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            String message = "Appointment with ID " + id + " not found";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

}
