package com.hospital.management.hospitalmanagementsystem.services;

import com.hospital.management.hospitalmanagementsystem.exceptions.AppointmentNotFoundException;
import com.hospital.management.hospitalmanagementsystem.exceptions.DoctorNotFoundException;
import com.hospital.management.hospitalmanagementsystem.exceptions.PatientNotFoundException;
import com.hospital.management.hospitalmanagementsystem.models.Appointment;
import com.hospital.management.hospitalmanagementsystem.models.Doctor;
import com.hospital.management.hospitalmanagementsystem.models.Patient;
import com.hospital.management.hospitalmanagementsystem.repositories.AppointmentRepository;
import com.hospital.management.hospitalmanagementsystem.repositories.DoctorRepository;
import com.hospital.management.hospitalmanagementsystem.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentServiceImpl(
            AppointmentRepository appointmentRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository) {

        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {

        Patient patient = patientRepository.findById(
                appointment.getPatient().getPatientId()
        ).orElseThrow(() ->
                new PatientNotFoundException(
                        "Patient with id "
                                + appointment.getPatient().getPatientId()
                                + " not found"
                )
        );

        Doctor doctor = doctorRepository.findById(
                appointment.getDoctor().getDoctorId()
        ).orElseThrow(() ->
                new DoctorNotFoundException(
                        "Doctor with id "
                                + appointment.getDoctor().getDoctorId()
                                + " not found"
                )
        );

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setStatus("Scheduled");

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new AppointmentNotFoundException(
                                "Appointment with id " + id + " not found"
                        )
                );
    }

    @Override
    public String deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new AppointmentNotFoundException(
                                "Appointment with id " + id + " not found"
                        )
                );

        appointmentRepository.delete(appointment);

        return "Appointment with id " + id + " deleted successfully";
    }

    @Override
    public String updateAppointment(Long id, Appointment appointment) {

        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new AppointmentNotFoundException(
                                "Appointment with id " + id + " not found"
                        )
                );

        existingAppointment.setAppointmentDate(
                appointment.getAppointmentDate()
        );

        existingAppointment.setAppointmentTime(
                appointment.getAppointmentTime()
        );

        existingAppointment.setReason(
                appointment.getReason()
        );

        existingAppointment.setStatus(
                appointment.getStatus()
        );

        existingAppointment.setPatient(
                appointment.getPatient()
        );

        existingAppointment.setDoctor(
                appointment.getDoctor()
        );

        appointmentRepository.save(existingAppointment);

        return "Appointment with id " + id + " updated successfully";
    }
}
