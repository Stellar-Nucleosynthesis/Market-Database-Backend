package tech.zlagoda.market_database_backend.validators;

import tech.zlagoda.market_database_backend.pojos.Credentials;

import static tech.zlagoda.market_database_backend.validators.ValidationUtils.validateString;

public class CredentialsValidator {
    public static void validate(Credentials credentials) {
        if (credentials == null) {
            throw new IllegalArgumentException("Illegal credentials");
        }
        validateString(credentials.getUsername(), "username", 1, 50);
    }
}
