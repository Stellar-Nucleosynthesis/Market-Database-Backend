package tech.zlagoda.market_database_backend.error_handling;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.zlagoda.market_database_backend.controllers.AuthController;

@RestControllerAdvice(assignableTypes = {AuthController.class})
public class AuthErrorHandler {
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> exceptionHandler(Throwable e) {
        String reason = "ERROR: ";
        if (e instanceof UsernameNotFoundException) {
            reason += e.getMessage();
        } else {
            reason += "Unable to log in";
        }
        return ResponseEntity.badRequest().body(reason);
    }
}