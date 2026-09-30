package com.hospital.management.hospitalmanagementsystem.services;

import com.hospital.management.hospitalmanagementsystem.models.Bill;

import java.util.List;

public interface BillService {

    List<Bill> getAllBills();

    Bill createBill(Bill bill);

    Bill getBillById(Long id);

    String deleteBill(Long id);

    String updateBill(Long id, Bill bill);
}
