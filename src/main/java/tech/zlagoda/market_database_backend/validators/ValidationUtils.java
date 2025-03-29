package tech.zlagoda.market_database_backend.validators;

import java.math.BigDecimal;

public class ValidationUtils {
    public static void validateString(String value, String fieldName, int minLength, int maxLength) {
        if (value == null || value.length() < minLength || value.length() > maxLength) {
            throw new IllegalArgumentException("Illegal " + fieldName);
        }
    }

    public static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() > 13) {
            throw new IllegalArgumentException("Illegal phone number");
        }
    }

    public static void validatePercent(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Illegal discount percent");
        }
    }

    public static void validateDecimal(BigDecimal num, String fieldName, int min) {
        if (num == null || num.compareTo(new BigDecimal(min)) < 0) {
            throw new IllegalArgumentException("Illegal " + fieldName);
        }
    }
}
