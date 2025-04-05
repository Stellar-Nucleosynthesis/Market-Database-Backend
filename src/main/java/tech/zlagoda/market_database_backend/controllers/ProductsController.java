package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.Product;
import tech.zlagoda.market_database_backend.repositories.ProductsRepository;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    ProductsController(ProductsRepository repository) {
        this.repository = repository;
    }

    private final ProductsRepository repository;

    @Secured({"Manager"})
    @PostMapping
    public ResponseEntity<Integer> addProduct(@RequestBody Product product) {
        repository.addProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(product.getIdProduct());
    }

    @Secured({"Manager"})
    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Integer> deleteCategory(@PathVariable int idProduct) {
        repository.deleteProduct(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body(idProduct);
    }

    @Secured({"Manager"})
    @PutMapping("/{idProduct}")
    public ResponseEntity<Integer> updateProduct(@RequestBody Product product, @PathVariable int idProduct) {
        product.setIdProduct(idProduct);
        repository.updateProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(product.getIdProduct());
    }

    @Secured({"Manager", "Cashier"})
    @GetMapping("/search")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) Integer categoryNumber,
            @RequestParam(required = false) String sortBy) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.getProducts(productName, categoryNumber, sortBy));
    }
}
