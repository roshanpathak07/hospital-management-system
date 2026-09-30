package com.hospital.management.hospitalmanagementsystem.repositories;

import com.hospital.management.hospitalmanagementsystem.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
