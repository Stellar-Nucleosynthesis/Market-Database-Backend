package tech.zlagoda.market_database_backend.validators;

import tech.zlagoda.market_database_backend.pojos.StoreProduct;

import static tech.zlagoda.market_database_backend.validators.ValidationUtils.validateDecimal;
import static tech.zlagoda.market_database_backend.validators.ValidationUtils.validateString;

public class StoreProductValidator {
    public static void validate(StoreProduct storeProduct) {
        if (storeProduct == null) {
            throw new IllegalArgumentException("Illegal store product information");
        }
        validateString(storeProduct.getUpc(), "store product UPC", 1, 12);
        validateString(storeProduct.getUpcProm(), "store product UPC prom", 1, 12);
        validateDecimal(storeProduct.getSellingPrice(), "store product selling price", 0);
        if (storeProduct.getProductsNumber() <= 0) {
            throw new IllegalArgumentException("Illegal number of store products");
        }
    }
}
