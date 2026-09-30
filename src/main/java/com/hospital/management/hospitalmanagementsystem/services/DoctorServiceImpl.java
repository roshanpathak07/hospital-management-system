package com.hospital.management.hospitalmanagementsystem.services;

import com.hospital.management.hospitalmanagementsystem.exceptions.DoctorNotFoundException;
import com.hospital.management.hospitalmanagementsystem.models.Doctor;
import com.hospital.management.hospitalmanagementsystem.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService{

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(()->
                        new DoctorNotFoundException(
                        "Doctor with id " + id + " not found"
                        ));
        return doctor;
    }

    @Override
    public String deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(()->
                        new DoctorNotFoundException(
                        "Doctor with id " + id + " not found"
                ));

        doctorRepository.delete(doctor);

        return "Doctor with id " + id + " deleted successfully";
    }

    @Override
    public String updateDoctor(Long id, Doctor doctor) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(()->
                        new DoctorNotFoundException(
                        "Doctor with id " + id + " not found"
                        ));
        existingDoctor.setName(doctor.getName());
        existingDoctor.setSpeciality(doctor.getSpeciality());
        doctorRepository.save(existingDoctor);
        return "Doctor with id " + id + " updated successfully";
    }
}
