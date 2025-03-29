package tech.zlagoda.market_database_backend.validators;

import tech.zlagoda.market_database_backend.pojos.Receipt;
import tech.zlagoda.market_database_backend.pojos.Sale;

import java.math.BigDecimal;

import static tech.zlagoda.market_database_backend.validators.ValidationUtils.validateDecimal;
import static tech.zlagoda.market_database_backend.validators.ValidationUtils.validateString;

public class ReceiptValidator {
    public static void validate(Receipt receipt) {
        if (receipt == null) {
            throw new IllegalArgumentException("Illegal receipt information");
        }
        validateString(receipt.getReceiptNumber(), "receipt number", 0,10);
        validateString(receipt.getIdEmployee(), "receipt employee ID", 0,10);
        validateString(receipt.getCardNumber(), "receipt card number", 0,13);
        if (receipt.getPrintDate() == null) {
            throw new IllegalArgumentException("Illegal receipt print date");
        }
        validateDecimal(receipt.getSumTotal(), "receipt total sum", 0);
        validateDecimal(receipt.getVat(), "receipt VAT amount", 0);
        for(Sale sale : receipt.getSales()){
            SaleValidator.validate(sale);
            if(receipt.getReceiptNumber().equals(sale.getReceiptNumber())){
                throw new IllegalArgumentException("Conflicting receipt number");
            }
        }
    }
}
