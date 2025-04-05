package tech.zlagoda.market_database_backend.validators;

import tech.zlagoda.market_database_backend.pojos.UserInfo;

import static tech.zlagoda.market_database_backend.validators.ValidationUtils.validateString;

public class UserInfoValidator {
    public static void validate(UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalArgumentException("Illegal user information");
        }
        validateString(userInfo.getUsername(), "username", 0, 50);
        EmployeeValidator.validate(userInfo.getEmployee());
    }
}
