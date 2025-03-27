package tech.zlagoda.market_database_backend.error_handling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralErrorHandler {
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> exceptionHandler(Throwable e) {
        String reason = e.getMessage();
        return ResponseEntity
                .badRequest()
                .body("ERROR: " + reason);
    }
}
