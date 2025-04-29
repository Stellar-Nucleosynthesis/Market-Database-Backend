package tech.zlagoda.market_database_backend.error_handling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.zlagoda.market_database_backend.controllers.ProductsController;
import tech.zlagoda.market_database_backend.pojos.RequestResponse;

@RestControllerAdvice(assignableTypes = {ProductsController.class})
public class ProductsErrorHandler {
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<RequestResponse> exceptionHandler(Throwable e) {
        String reason = "ERROR: ";
        if (e instanceof IllegalArgumentException) {
            reason += e.getMessage();
        } else {
            reason += "Unable to query products information";
        }
        return ResponseEntity.badRequest().body(new RequestResponse(null, false, reason));
    }
}
