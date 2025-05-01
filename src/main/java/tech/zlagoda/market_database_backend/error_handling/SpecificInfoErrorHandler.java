package tech.zlagoda.market_database_backend.error_handling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.zlagoda.market_database_backend.controllers.CategoriesController;
import tech.zlagoda.market_database_backend.controllers.SpecificInfoController;
import tech.zlagoda.market_database_backend.pojos.RequestResponse;

@RestControllerAdvice(assignableTypes = {SpecificInfoController.class})
public class SpecificInfoErrorHandler {
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<RequestResponse> exceptionHandler(Throwable e) {
        String reason = "ERROR: ";
        if (e instanceof IllegalArgumentException) {
            reason += e.getMessage();
        } else {
            reason += "Unable to get specific information";
        }
        return ResponseEntity.badRequest().body(new RequestResponse(null, false, reason));
    }
}