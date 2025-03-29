package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.CustomerCard;
import tech.zlagoda.market_database_backend.repositories.CustomerCardsRepository;
import tech.zlagoda.market_database_backend.security.EmployeeCheck;

import java.util.List;

@RestController
@RequestMapping("/customer-cards")
public class CustomerCardsController {

    @Autowired
    CustomerCardsController(CustomerCardsRepository repository) {
        this.repository = repository;
    }

    private final CustomerCardsRepository repository;

    @EmployeeCheck
    @PostMapping
    public ResponseEntity<String> addCustomerCard(@RequestBody CustomerCard customerCard) {
        repository.addCustomerCard(customerCard);
        return ResponseEntity.status(HttpStatus.OK).body(customerCard.getCardNumber());
    }

    @EmployeeCheck
    @DeleteMapping("/{cardNumber}")
    public ResponseEntity<String> deleteCustomerCard(@PathVariable String cardNumber) {
        repository.deleteCustomerCard(cardNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardNumber);
    }

    @EmployeeCheck
    @PutMapping("/{cardNumber}")
    public ResponseEntity<String> updateCustomerCard(
            @RequestBody CustomerCard customerCard,
            @PathVariable String cardNumber) {
        customerCard.setCardNumber(cardNumber);
        repository.updateCustomerCard(customerCard);
        return ResponseEntity.status(HttpStatus.OK).body(customerCard.getCardNumber());
    }

    @EmployeeCheck
    @GetMapping("/search")
    public ResponseEntity<List<CustomerCard>> getCustomerCards(
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) Integer percent) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.getCustomerCards(surname, percent));
    }
}