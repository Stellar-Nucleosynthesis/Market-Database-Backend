package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.Receipt;
import tech.zlagoda.market_database_backend.repositories.ReceiptsRepository;
import tech.zlagoda.market_database_backend.security.CashierCheck;
import tech.zlagoda.market_database_backend.security.EmployeeCheck;
import tech.zlagoda.market_database_backend.security.ManagerCheck;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/receipts")
class ReceiptsController {
    @Autowired
    public ReceiptsController(ReceiptsRepository repository) {
        this.repository = repository;
    }

    private final ReceiptsRepository repository;

    @CashierCheck
    @PostMapping
    public ResponseEntity<String> addReceipt(@RequestBody Receipt receipt) {
        repository.addReceipt(receipt);
        return ResponseEntity.status(HttpStatus.OK).body(receipt.getReceiptNumber());
    }

    @ManagerCheck
    @DeleteMapping("/{receiptNumber}")
    public ResponseEntity<String> deleteReceipt(@PathVariable String receiptNumber) {
        repository.deleteReceipt(receiptNumber);
        return ResponseEntity.status(HttpStatus.OK).body(receiptNumber);
    }
}
