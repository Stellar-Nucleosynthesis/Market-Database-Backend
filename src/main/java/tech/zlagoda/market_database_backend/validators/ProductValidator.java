package tech.zlagoda.market_database_backend.validators;

import tech.zlagoda.market_database_backend.pojos.Product;

import static tech.zlagoda.market_database_backend.validators.ValidationUtils.validateString;

public class ProductValidator {
    public static void validate(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Illegal product information");
        }
        validateString(product.getProductName(), "product name", 0, 50);
        validateString(product.getManufacturer(), "product manufacturer", 0, 50);
        validateString(product.getCharacteristics(), "product characteristics", 0, 100);
    }
}
