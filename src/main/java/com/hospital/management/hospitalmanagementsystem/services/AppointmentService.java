package com.hospital.management.hospitalmanagementsystem.services;

import com.hospital.management.hospitalmanagementsystem.models.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAllAppointments();
    Appointment createAppointment(Appointment appointment);
    Appointment getAppointmentById(Long id);
    String deleteAppointment(Long id);
    String updateAppointment(Long id, Appointment appointment);
}
