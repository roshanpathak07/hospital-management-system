package com.hospital.management.hospitalmanagementsystem.repositories;

import com.hospital.management.hospitalmanagementsystem.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
