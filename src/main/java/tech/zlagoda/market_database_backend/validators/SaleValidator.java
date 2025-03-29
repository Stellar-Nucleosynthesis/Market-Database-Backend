package tech.zlagoda.market_database_backend.validators;

import tech.zlagoda.market_database_backend.pojos.Sale;

import java.math.BigDecimal;

import static tech.zlagoda.market_database_backend.validators.ValidationUtils.validateDecimal;
import static tech.zlagoda.market_database_backend.validators.ValidationUtils.validateString;

public class SaleValidator {
    public static void validate(Sale sale){
        if (sale == null) {
            throw new IllegalArgumentException("Illegal sale information");
        }
        validateString(sale.getReceiptNumber(), "receipt number", 0, 10);
        if(sale.getProductNumber() < 0){
            throw new IllegalArgumentException("Illegal product number");
        }
        validateDecimal(sale.getSellingPrice(), "selling price", 0);
    }
}
