package tech.zlagoda.market_database_backend.validators;

import tech.zlagoda.market_database_backend.pojos.Sale;

import java.math.BigDecimal;

public class SaleValidator {
    public static void validate(Sale sale){
        if (sale == null) {
            throw new IllegalArgumentException("Illegal sale information");
        }
        String receiptNum = sale.getReceiptNumber();
        if (receiptNum == null || receiptNum.length() > 10 || receiptNum.isEmpty()) {
            throw new IllegalArgumentException("Illegal receipt number");
        }
        BigDecimal sellingPrice = sale.getSellingPrice();
        if (sellingPrice == null || sellingPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Illegal selling price");
        }
    }
}
