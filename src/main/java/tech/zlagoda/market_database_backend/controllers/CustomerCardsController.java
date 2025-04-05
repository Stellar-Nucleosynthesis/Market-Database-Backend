package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.CustomerCard;
import tech.zlagoda.market_database_backend.services.CustomerCardsService;

import java.util.List;

@RestController
@RequestMapping("/customer-cards")
public class CustomerCardsController {

    @Autowired
    CustomerCardsController(CustomerCardsService service) {
        this.service = service;
    }

    private final CustomerCardsService service;

    @Secured({"Manager", "Cashier"})
    @PostMapping
    public ResponseEntity<String> addCustomerCard(@RequestBody CustomerCard customerCard) {
        return ResponseEntity.status(HttpStatus.OK).body(service.addCustomerCard(customerCard));
    }

    @Secured({"Manager", "Cashier"})
    @DeleteMapping("/{cardNumber}")
    public ResponseEntity<String> deleteCustomerCard(@PathVariable String cardNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteCustomerCard(cardNumber));
    }

    @Secured({"Manager", "Cashier"})
    @PutMapping("/{cardNumber}")
    public ResponseEntity<String> updateCustomerCard(
            @RequestBody CustomerCard customerCard,
            @PathVariable String cardNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateCustomerCard(customerCard, cardNumber));
    }

    @Secured({"Manager", "Cashier"})
    @GetMapping("/search")
    public ResponseEntity<List<CustomerCard>> getCustomerCards(
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) Integer percent) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getCustomerCards(surname, percent));
    }
}