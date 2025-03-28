package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.StoreProduct;
import tech.zlagoda.market_database_backend.repositories.StoreProductsRepository;
import tech.zlagoda.market_database_backend.security.EmployeeCheck;
import tech.zlagoda.market_database_backend.security.ManagerCheck;

import java.util.List;

@RestController
@RequestMapping("/store-products")
public class StoreProductsController {
    private final StoreProductsRepository repository;

    @Autowired
    StoreProductsController(StoreProductsRepository repository) {
        this.repository = repository;
    }

    @ManagerCheck
    @PostMapping
    public ResponseEntity<String> addStoreProduct(@RequestBody StoreProduct storeProduct) {
        repository.addStoreProduct(storeProduct);
        return ResponseEntity.status(HttpStatus.OK).body(storeProduct.getUpc());
    }

    @ManagerCheck
    @DeleteMapping("/{upc}")
    public ResponseEntity<String> deleteStoreProduct(@PathVariable String upc) {
        repository.deleteStoreProduct(upc);
        return ResponseEntity.status(HttpStatus.OK).body(upc);
    }

    @ManagerCheck
    @PutMapping("/{upc}")
    public ResponseEntity<String> updateStoreProduct(@RequestBody StoreProduct storeProduct, @PathVariable String upc) {
        storeProduct.setUpc(upc);
        repository.updateStoreProduct(storeProduct);
        return ResponseEntity.status(HttpStatus.OK).body(storeProduct.getUpc());
    }

    @EmployeeCheck
    @GetMapping("/search")
    public ResponseEntity<List<StoreProduct>> getStoreProducts(@RequestParam(required = false) String upc, @RequestParam(required = false) Boolean promotional) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.getStoreProducts(upc, promotional));
    }
}
