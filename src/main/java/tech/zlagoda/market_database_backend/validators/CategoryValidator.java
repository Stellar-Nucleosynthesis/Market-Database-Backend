package tech.zlagoda.market_database_backend.validators;

import tech.zlagoda.market_database_backend.pojos.Category;

import java.math.BigInteger;

public class CategoryValidator {
    public static void validate(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Illegal category information");
        }
        String name = category.getCategoryName();
        if(name == null || name.isEmpty() || name.length() > 50) {
            throw new IllegalArgumentException("Illegal category name");
        }
    }
}
