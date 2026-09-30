package com.hospital.management.hospitalmanagementsystem.services;

import com.hospital.management.hospitalmanagementsystem.exceptions.BillNotFoundException;
import com.hospital.management.hospitalmanagementsystem.models.Bill;
import com.hospital.management.hospitalmanagementsystem.repositories.BillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill createBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill getBillById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() ->
                        new BillNotFoundException(
                                "Bill with id " + id + " not found"
                        )
                );
    }

    @Override
    public String deleteBill(Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() ->
                        new BillNotFoundException(
                                "Bill with id " + id + " not found"
                        )
                );

        billRepository.delete(bill);

        return "Bill with id " + id + " deleted successfully";
    }

    @Override
    public String updateBill(Long id, Bill bill) {

        Bill existingBill = billRepository.findById(id)
                .orElseThrow(() ->
                        new BillNotFoundException(
                                "Bill with id " + id + " not found"
                        )
                );

        existingBill.setAmount(bill.getAmount());
        existingBill.setPatientId(bill.getPatientId());
        existingBill.setStatus(bill.getStatus());

        billRepository.save(existingBill);

        return "Bill with id " + id + " updated successfully";
    }
}
