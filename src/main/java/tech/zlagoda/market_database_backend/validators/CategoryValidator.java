package tech.zlagoda.market_database_backend.validators;

import tech.zlagoda.market_database_backend.pojos.Category;

import static tech.zlagoda.market_database_backend.validators.ValidationUtils.validateString;

public class CategoryValidator {
    public static void validate(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Illegal category information");
        }
        validateString(category.getCategoryName(), "category name", 0, 50);
    }
}
