package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.Product;
import tech.zlagoda.market_database_backend.repositories.ProductsRepository;
import tech.zlagoda.market_database_backend.security.EmployeeCheck;
import tech.zlagoda.market_database_backend.security.ManagerCheck;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    ProductsController(ProductsRepository repository) {
        this.repository = repository;
    }

    private final ProductsRepository repository;

    @ManagerCheck
    @PostMapping
    public ResponseEntity<Integer> addProduct(@RequestBody Product product) {
        repository.addProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(product.getIdProduct());
    }

    @ManagerCheck
    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Integer> deleteCategory(@PathVariable int idProduct) {
        repository.deleteProduct(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body(idProduct);
    }

    @ManagerCheck
    @PutMapping("/{idProduct}")
    public ResponseEntity<Integer> changeProduct(@RequestBody Product product, @PathVariable int idProduct) {
        product.setIdProduct(idProduct);
        repository.changeProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(product.getIdProduct());
    }

    @EmployeeCheck
    @GetMapping("/search")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) Integer categoryNumber){
        return ResponseEntity.status(HttpStatus.OK).body(repository.getProducts(productName, categoryNumber));
    }
}
