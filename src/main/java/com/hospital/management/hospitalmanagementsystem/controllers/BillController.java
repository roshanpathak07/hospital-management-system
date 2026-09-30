package com.hospital.management.hospitalmanagementsystem.controllers;

import com.hospital.management.hospitalmanagementsystem.models.Bill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills() {
        List<Bill> bills = billService.getAllBills();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
        Bill createdBill = billService.createBill(bill);
        return new ResponseEntity<>(createdBill, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBillById(@PathVariable Long id) {
        Bill bill = billService.getBillById(id);

        if (bill != null) {
            return new ResponseEntity<>(bill, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    "Bill with ID " + id + " not found",
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBill(@PathVariable Long id) {
        Bill bill = billService.getBillById(id);

        if (bill != null) {
            billService.deleteBill(id);
            return new ResponseEntity<>(
                    "Bill Deleted Successfully",
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    "Bill with ID " + id + " not found",
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBill(
            @PathVariable Long id,
            @RequestBody Bill bill) {

        Bill existingBill = billService.getBillById(id);

        if (existingBill != null) {
            billService.updateBill(id, bill);
            return new ResponseEntity<>(
                    "Bill Updated Successfully",
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    "Bill with ID " + id + " not found",
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
