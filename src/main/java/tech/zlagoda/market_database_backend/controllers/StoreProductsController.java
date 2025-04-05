package tech.zlagoda.market_database_backend.controllers;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.StoreProduct;
import tech.zlagoda.market_database_backend.pojos.StoreProductInfo;
import tech.zlagoda.market_database_backend.repositories.StoreProductsRepository;

import java.util.List;

@RestController
@RequestMapping("/store-products")
public class StoreProductsController {
    @Autowired
    StoreProductsController(StoreProductsRepository repository) {
        this.repository = repository;
    }

    private final StoreProductsRepository repository;

    @RolesAllowed({"Manager"})
    @PostMapping
    public ResponseEntity<String> addStoreProduct(@RequestBody StoreProduct storeProduct) {
        repository.addStoreProduct(storeProduct);
        return ResponseEntity.status(HttpStatus.OK).body(storeProduct.getUpc());
    }

    @RolesAllowed({"Manager"})
    @DeleteMapping("/{upc}")
    public ResponseEntity<String> deleteStoreProduct(@PathVariable String upc) {
        repository.deleteStoreProduct(upc);
        return ResponseEntity.status(HttpStatus.OK).body(upc);
    }

    @RolesAllowed({"Manager"})
    @PutMapping("/{upc}")
    public ResponseEntity<String> updateStoreProduct(@RequestBody StoreProduct storeProduct, @PathVariable String upc) {
        storeProduct.setUpc(upc);
        repository.updateStoreProduct(storeProduct);
        return ResponseEntity.status(HttpStatus.OK).body(storeProduct.getUpc());
    }

    @RolesAllowed({"Manager", "Cashier"})
    @GetMapping("/search")
    public ResponseEntity<List<StoreProduct>> getStoreProducts(
            @RequestParam(required = false) Boolean promotional,
            @RequestParam(required = false) String sortBy) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.getStoreProducts(promotional, sortBy));
    }

    @RolesAllowed({"Manager", "Cashier"})
    @GetMapping("/search/{upc}")
    public ResponseEntity<StoreProductInfo> getStoreProductInfo(@PathVariable String upc) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.getStoreProductInfo(upc));
    }
}
