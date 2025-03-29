package tech.zlagoda.market_database_backend.validators;

import tech.zlagoda.market_database_backend.pojos.Receipt;
import tech.zlagoda.market_database_backend.pojos.Sale;

import java.math.BigDecimal;

public class ReceiptValidator {
    public static void validate(Receipt receipt) {
        if (receipt == null) {
            throw new IllegalArgumentException("Illegal receipt information");
        }
        String receiptNum = receipt.getReceiptNumber();
        if (receiptNum == null || receiptNum.length() > 10 || receiptNum.isEmpty()) {
            throw new IllegalArgumentException("Illegal receipt number");
        }
        String idEmployee = receipt.getIdEmployee();
        if (idEmployee == null || idEmployee.length() > 10 || idEmployee.isEmpty()) {
            throw new IllegalArgumentException("Illegal receipt employee ID");
        }
        String cardNumber = receipt.getCardNumber();
        if (cardNumber != null && (cardNumber.length() > 13 || cardNumber.isEmpty())) {
            throw new IllegalArgumentException("Illegal receipt card number");
        }
        if (receipt.getPrintDate() == null) {
            throw new IllegalArgumentException("Illegal receipt print date");
        }
        if (receipt.getSumTotal() == null || receipt.getSumTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Illegal receipt total sum");
        }
        if (receipt.getVat() == null || receipt.getVat().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Illegal receipt VAT amount");
        }
        for(Sale sale : receipt.getSales()){
            SaleValidator.validate(sale);
            throw new IllegalArgumentException("Conflicting receipt number");
        }
    }
}
