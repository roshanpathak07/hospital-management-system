package com.hospital.management.hospitalmanagementsystem.repositories;

import com.hospital.management.hospitalmanagementsystem.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}
