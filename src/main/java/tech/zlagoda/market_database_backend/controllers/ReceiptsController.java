package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.Receipt;
import tech.zlagoda.market_database_backend.services.ReceiptsService;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/receipts")
public class ReceiptsController {
    @Autowired
    public ReceiptsController(ReceiptsService service) {
        this.service = service;
    }

    private final ReceiptsService service;

    @Secured({"Cashier"})
    @PostMapping
    public ResponseEntity<String> addReceipt(@RequestBody Receipt receipt) {
        return ResponseEntity.status(HttpStatus.OK).body(service.addReceipt(receipt));
    }

    @Secured({"Manager"})
    @DeleteMapping("/{receiptNumber}")
    public ResponseEntity<String> deleteReceipt(@PathVariable String receiptNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteReceipt(receiptNumber));
    }

    @Secured({"Manager"})
    @GetMapping("/search")
    public ResponseEntity<List<Receipt>> getReceipts(
            @RequestParam(required = false) String idEmployee,
            @RequestParam(required = false) Date from,
            @RequestParam(required = false) Date to) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getReceipts(idEmployee, from, to));
    }

    @Secured({"Manager", "Cashier"})
    @GetMapping("/search/{receiptNumber}")
    public ResponseEntity<Receipt> getReceipt(@PathVariable String receiptNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getReceipt(receiptNumber));
    }

    @Secured({"Manager"})
    @GetMapping("/total")
    public ResponseEntity<BigDecimal> getTotal(
            @RequestParam(required = false) String idEmployee,
            @RequestParam(required = false) Date from,
            @RequestParam(required = false) Date to){
        return ResponseEntity.status(HttpStatus.OK).body(service.getTotal(idEmployee, from, to));
    }

    @Secured({"Manager"})
    @GetMapping("/quantity/{upc}")
    public ResponseEntity<Integer> getQuantity(
            @PathVariable String upc,
            @RequestParam(required = false) Date from,
            @RequestParam(required = false) Date to) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getQuantity(upc, from, to));
    }

    @Secured({"Cashier"})
    @GetMapping("/me")
    public ResponseEntity<List<Receipt>> getMyReceipts(@RequestParam(required = false) Date from,
                                                       @RequestParam(required = false) Date to){
        return ResponseEntity.status(HttpStatus.OK).body(service.getMyReceipts(from, to));
    }
}
