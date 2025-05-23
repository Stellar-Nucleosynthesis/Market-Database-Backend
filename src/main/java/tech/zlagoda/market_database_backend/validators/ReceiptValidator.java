package tech.zlagoda.market_database_backend.validators;

import static tech.zlagoda.market_database_backend.validators.ValidationUtils.validateDecimal;
import static tech.zlagoda.market_database_backend.validators.ValidationUtils.validateString;

import tech.zlagoda.market_database_backend.pojos.Receipt;
import tech.zlagoda.market_database_backend.pojos.Sale;

public class ReceiptValidator {
    public static void validate(Receipt receipt) {
        if (receipt == null) {
            throw new IllegalArgumentException("Illegal receipt information");
        }
        validateString(receipt.getReceiptNumber(), "receipt number", 1,10);
        validateString(receipt.getIdEmployee(), "receipt employee ID", 1,10);
        if(receipt.getCardNumber() != null) {
            validateString(receipt.getCardNumber(), "customer card number", 1,13);
        }
        if (receipt.getPrintDate() == null) {
            throw new IllegalArgumentException("Illegal receipt print date");
        }
        validateDecimal(receipt.getSumTotal(), "receipt total sum", 0);
        validateDecimal(receipt.getVat(), "receipt VAT amount", 0);
        for(Sale sale : receipt.getSales()){
            SaleValidator.validate(sale);
            if (sale.getReceiptNumber() != null &&
					!sale.getReceiptNumber().isEmpty() &&
					!receipt.getReceiptNumber().equals(sale.getReceiptNumber())) {
				throw new IllegalArgumentException("Sale receipt number (" +
						sale.getReceiptNumber() + ") doesn't match parent receipt number (" +
						receipt.getReceiptNumber() + ")");
			}
        }
    }
}
