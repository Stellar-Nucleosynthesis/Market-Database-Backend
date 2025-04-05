package tech.zlagoda.market_database_backend.error_handling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.zlagoda.market_database_backend.controllers.UserInfoController;

@RestControllerAdvice(assignableTypes = {UserInfoController.class})
public class UserInfoErrorHandler {
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> exceptionHandler(Throwable e) {
        String reason = "ERROR: ";
        if (e instanceof IllegalArgumentException) {
            reason += e.getMessage();
        } else {
            reason += "Unable to query login credentials";
        }
        return ResponseEntity.badRequest().body(reason);
    }
}