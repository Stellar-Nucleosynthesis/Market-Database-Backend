package tech.zlagoda.market_database_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.zlagoda.market_database_backend.pojos.CustomerCard;
import tech.zlagoda.market_database_backend.repositories.CustomerCardsRepository;

import java.util.List;

import static tech.zlagoda.market_database_backend.validators.CustomerCardValidator.validate;

@Service
public class CustomerCardsService {

    @Autowired
    CustomerCardsService(CustomerCardsRepository repository) {
        this.repository = repository;
    }

    private final CustomerCardsRepository repository;

    public String addCustomerCard(CustomerCard customerCard) {
        validate(customerCard);
        repository.addCustomerCard(customerCard);
        return customerCard.getCardNumber();
    }

    public String deleteCustomerCard(String cardNumber) {
        repository.deleteCustomerCard(cardNumber);
        return cardNumber;
    }

    public String updateCustomerCard(CustomerCard customerCard, String cardNumber) {
        validate(customerCard);
        customerCard.setCardNumber(cardNumber);
        repository.updateCustomerCard(customerCard);
        return customerCard.getCardNumber();
    }

    public List<CustomerCard> getCustomerCards(String surname, Integer percent) {
        return repository.getCustomerCards(surname, percent);
    }
}