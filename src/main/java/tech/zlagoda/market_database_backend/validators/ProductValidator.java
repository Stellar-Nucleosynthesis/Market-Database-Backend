package tech.zlagoda.market_database_backend.validators;

import tech.zlagoda.market_database_backend.pojos.Product;

public class ProductValidator {
    public static void validate(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Illegal product information");
        }
        String productName = product.getProductName();
        if(productName == null || productName.isEmpty() || productName.length() > 50) {
            throw new IllegalArgumentException("Illegal product name");
        }
        String manufacturer = product.getManufacturer();
        if(manufacturer == null || manufacturer.isEmpty() || manufacturer.length() > 50) {
            throw new IllegalArgumentException("Illegal product manufacturer");
        }
        String characteristics = product.getCharacteristics();
        if(characteristics == null || characteristics.isEmpty() || characteristics.length() > 100) {
            throw new IllegalArgumentException("Illegal product characteristics");
        }
    }
}
