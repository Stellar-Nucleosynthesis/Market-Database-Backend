package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.Product;
import tech.zlagoda.market_database_backend.pojos.RequestResponse;
import tech.zlagoda.market_database_backend.services.ProductsService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    ProductsController(ProductsService service) {
        this.service = service;
    }

    private final ProductsService service;

    @Secured({"Manager"})
    @PostMapping
    public ResponseEntity<RequestResponse> addProduct(@RequestBody Product product) {
        String id = String.valueOf(service.addProduct(product));
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse(id, true, null));
    }

    @Secured({"Manager"})
    @DeleteMapping("/{idProduct}")
    public ResponseEntity<RequestResponse> deleteProduct(@PathVariable int idProduct) {
        String id = String.valueOf(service.deleteProduct(idProduct));
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse(id, true, null));
    }

    @Secured({"Manager"})
    @PutMapping("/{idProduct}")
    public ResponseEntity<RequestResponse> updateProduct(@RequestBody Product product, @PathVariable int idProduct) {
        String id = String.valueOf(service.updateProduct(product, idProduct));
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse(id, true, null));
    }

    @Secured({"Cashier"})
    @GetMapping("/search")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) Integer categoryNumber,
            @RequestParam(required = false) String sortBy) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getProducts(productName, categoryNumber, sortBy));
    }
}
