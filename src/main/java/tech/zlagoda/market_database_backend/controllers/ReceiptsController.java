package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.Receipt;
import tech.zlagoda.market_database_backend.pojos.Sale;
import tech.zlagoda.market_database_backend.repositories.ReceiptsRepository;
import tech.zlagoda.market_database_backend.repositories.SalesRepository;
import tech.zlagoda.market_database_backend.security.CashierCheck;
import tech.zlagoda.market_database_backend.security.EmployeeCheck;
import tech.zlagoda.market_database_backend.security.ManagerCheck;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/receipts")
public class ReceiptsController {
    @Autowired
    public ReceiptsController(ReceiptsRepository receiptsRepository, SalesRepository salesRepository) {
        this.receiptsRepository = receiptsRepository;
        this.salesRepository = salesRepository;
    }

    private final ReceiptsRepository receiptsRepository;
    private final SalesRepository salesRepository;

    @CashierCheck
    @PostMapping
    public ResponseEntity<String> addReceipt(@RequestBody Receipt receipt) {
        receiptsRepository.addReceipt(receipt);
        for(Sale sale : receipt.getSales()) {
            salesRepository.addSale(sale);
        }
        return ResponseEntity.status(HttpStatus.OK).body(receipt.getReceiptNumber());
    }

    @ManagerCheck
    @DeleteMapping("/{receiptNumber}")
    public ResponseEntity<String> deleteReceipt(@PathVariable String receiptNumber) {
        receiptsRepository.deleteReceipt(receiptNumber);
        salesRepository.deleteSales(receiptNumber);
        return ResponseEntity.status(HttpStatus.OK).body(receiptNumber);
    }

    @ManagerCheck
    @GetMapping("/search")
    public ResponseEntity<List<Receipt>> getReceipts(
            @RequestParam(required = false) String idEmployee,
            @RequestParam(required = false) Date from,
            @RequestParam(required = false) Date to) {
        List<Receipt> receipts = receiptsRepository.getReceipts(idEmployee, from, to);
        for (Receipt receipt : receipts) {
            receipt.setSales(salesRepository.getSales(receipt.getReceiptNumber()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(receipts);
    }

    @EmployeeCheck
    @GetMapping("/search/{receiptNumber}")
    public ResponseEntity<List<Receipt>> getReceipt(@PathVariable String receiptNumber) {
        List<Receipt> receipts = receiptsRepository.getReceipt(receiptNumber);
        for (Receipt receipt : receipts) {
            receipt.setSales(salesRepository.getSales(receipt.getReceiptNumber()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(receipts);
    }

    @CashierCheck
    @GetMapping("/me")
    public ResponseEntity<List<Receipt>> getReceipts(@RequestParam(required = false) Date from,
                                                     @RequestParam(required = false) Date to){
        throw new UnsupportedOperationException("Unable to get information about \"me\"");
    }
}
