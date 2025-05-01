package tech.zlagoda.market_database_backend.validators;

import tech.zlagoda.market_database_backend.pojos.CustomerCard;

import static tech.zlagoda.market_database_backend.validators.ValidationUtils.*;

public class CustomerCardValidator {
    public static void validate(CustomerCard card) {
        if (card == null) {
            throw new IllegalArgumentException("Illegal customer card information");
        }
        validateString(card.getCardNumber(), "customer card number", 1, 13);
        validateString(card.getSurname(), "customer surname", 1, 50);
        validateString(card.getName(), "customer name", 1, 50);
        validateString(card.getPatronymic(), "customer patronymic", 0, 50);
        validatePhoneNumber(card.getPhoneNumber());
        validateString(card.getCity(), "customer city", 1, 50);
        validateString(card.getStreet(), "customer street", 1, 100);
        validateString(card.getZipCode(), "customer zip code", 5, 10);
        validatePercent(card.getPercent());
    }
}
