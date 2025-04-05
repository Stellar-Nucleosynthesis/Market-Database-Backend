package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.Receipt;
import tech.zlagoda.market_database_backend.pojos.Sale;
import tech.zlagoda.market_database_backend.repositories.ReceiptsRepository;
import tech.zlagoda.market_database_backend.repositories.SalesRepository;

import java.math.BigDecimal;
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

    @Secured({"Cashier"})
    @PostMapping
    public ResponseEntity<String> addReceipt(@RequestBody Receipt receipt) {
        receiptsRepository.addReceipt(receipt);
        for(Sale sale : receipt.getSales()) {
            salesRepository.addSale(sale);
        }
        return ResponseEntity.status(HttpStatus.OK).body(receipt.getReceiptNumber());
    }

    @Secured({"Manager"})
    @DeleteMapping("/{receiptNumber}")
    public ResponseEntity<String> deleteReceipt(@PathVariable String receiptNumber) {
        receiptsRepository.deleteReceipt(receiptNumber);
        salesRepository.deleteSales(receiptNumber);
        return ResponseEntity.status(HttpStatus.OK).body(receiptNumber);
    }

    @Secured({"Manager"})
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

    @Secured({"Manager", "Cashier"})
    @GetMapping("/search/{receiptNumber}")
    public ResponseEntity<Receipt> getReceipt(@PathVariable String receiptNumber) {
        Receipt receipt = receiptsRepository.getReceipt(receiptNumber);
        receipt.setSales(salesRepository.getSales(receipt.getReceiptNumber()));
        return ResponseEntity.status(HttpStatus.OK).body(receipt);
    }

    @Secured({"Manager"})
    @GetMapping("/total")
    public ResponseEntity<BigDecimal> getTotal(
            @RequestParam(required = false) String idEmployee,
            @RequestParam(required = false) Date from,
            @RequestParam(required = false) Date to){
        BigDecimal res = BigDecimal.ZERO;
        for(Receipt receipt : receiptsRepository.getReceipts(idEmployee, from, to)) {
            for(Sale sale : salesRepository.getSales(receipt.getReceiptNumber())) {
                res = res.add(sale.getSellingPrice().multiply(new BigDecimal(sale.getProductNumber())));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @Secured({"Manager"})
    @GetMapping("/quantity/{upc}")
    public ResponseEntity<Integer> getQuantity(
            @PathVariable String upc,
            @RequestParam(required = false) Date from,
            @RequestParam(required = false) Date to) {
        int res = 0;
        for(Receipt receipt : receiptsRepository.getReceipts(null, from, to)) {
            for(Sale sale : salesRepository.getSales(receipt.getReceiptNumber())) {
                if(sale.getUPC().equals(upc)) {
                    res += sale.getProductNumber();
                }
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @Secured({"Cashier"})
    @GetMapping("/me")
    public ResponseEntity<List<Receipt>> getReceipts(@RequestParam(required = false) Date from,
                                                     @RequestParam(required = false) Date to){
        throw new UnsupportedOperationException("Unable to get information about \"me\"");
    }
}
