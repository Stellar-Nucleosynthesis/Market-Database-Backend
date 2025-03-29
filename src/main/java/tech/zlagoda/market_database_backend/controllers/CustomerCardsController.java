package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.CustomerCard;
import tech.zlagoda.market_database_backend.repositories.CustomerCardsRepository;
import tech.zlagoda.market_database_backend.security.ManagerCheck;

import java.util.List;

@RestController
@RequestMapping("/customer-cards")
public class CustomerCardsController {
    private final PersistenceExceptionTranslationAutoConfiguration persistenceExceptionTranslationAutoConfiguration;

    @Autowired
    CustomerCardsController(CustomerCardsRepository repository, PersistenceExceptionTranslationAutoConfiguration persistenceExceptionTranslationAutoConfiguration) {
        this.repository = repository;
        this.persistenceExceptionTranslationAutoConfiguration = persistenceExceptionTranslationAutoConfiguration;
    }

    private final CustomerCardsRepository repository;

    @ManagerCheck
    @PostMapping
    public ResponseEntity<String> addCustomerCard(@RequestBody CustomerCard customerCard) {
        repository.addCustomerCard(customerCard);
        return ResponseEntity.status(HttpStatus.OK).body(customerCard.getCardNumber());
    }

    @ManagerCheck
    @DeleteMapping("/{cardNumber}")
    public ResponseEntity<String> deleteCustomerCard(@PathVariable String cardNumber) {
        repository.deleteCustomerCard(cardNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardNumber);
    }

    @ManagerCheck
    @PutMapping("/{cardNumber}")
    public ResponseEntity<String> updateCustomerCard(
            @RequestBody CustomerCard customerCard,
            @PathVariable String cardNumber) {
        customerCard.setCardNumber(cardNumber);
        repository.updateCustomerCard(customerCard);
        return ResponseEntity.status(HttpStatus.OK).body(customerCard.getCardNumber());
    }

    @ManagerCheck
    @GetMapping("/search")
    public ResponseEntity<List<CustomerCard>> getCustomerCards(
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) Integer percent) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.getCustomerCards(surname, percent));
    }
}