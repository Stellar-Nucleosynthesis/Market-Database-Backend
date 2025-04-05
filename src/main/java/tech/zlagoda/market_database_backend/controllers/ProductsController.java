package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.Product;
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
    public ResponseEntity<Integer> addProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.OK).body(service.addProduct(product));
    }

    @Secured({"Manager"})
    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Integer> deleteProduct(@PathVariable int idProduct) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteProduct(idProduct));
    }

    @Secured({"Manager"})
    @PutMapping("/{idProduct}")
    public ResponseEntity<Integer> updateProduct(@RequestBody Product product, @PathVariable int idProduct) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateProduct(product, idProduct));
    }

    @Secured({"Manager", "Cashier"})
    @GetMapping("/search")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) Integer categoryNumber,
            @RequestParam(required = false) String sortBy) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getProducts(productName, categoryNumber, sortBy));
    }
}
