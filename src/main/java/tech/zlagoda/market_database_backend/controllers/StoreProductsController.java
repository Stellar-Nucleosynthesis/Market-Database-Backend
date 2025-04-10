package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.RequestResponse;
import tech.zlagoda.market_database_backend.pojos.StoreProduct;
import tech.zlagoda.market_database_backend.pojos.StoreProductInfo;
import tech.zlagoda.market_database_backend.services.StoreProductsService;

import java.util.List;

@RestController
@RequestMapping("/store-products")
public class StoreProductsController {
    @Autowired
    StoreProductsController(StoreProductsService service) {
        this.service = service;
    }

    private final StoreProductsService service;

    @Secured({"Manager"})
    @PostMapping
    public ResponseEntity<RequestResponse> addStoreProduct(@RequestBody StoreProduct storeProduct) {
        String id = service.addStoreProduct(storeProduct);
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse(id, true, null));
    }

    @Secured({"Manager"})
    @DeleteMapping("/{upc}")
    public ResponseEntity<RequestResponse> deleteStoreProduct(@PathVariable String upc) {
        String id = service.deleteStoreProduct(upc);
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse(id, true, null));
    }

    @Secured({"Manager"})
    @PutMapping("/{upc}")
    public ResponseEntity<RequestResponse> updateStoreProduct(@RequestBody StoreProduct storeProduct,
                                                     @PathVariable String upc) {
        String id = service.updateStoreProduct(storeProduct, upc);
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse(id, true, null));
    }

    @Secured({"Manager", "Cashier"})
    @GetMapping("/search")
    public ResponseEntity<List<StoreProduct>> getStoreProducts(
            @RequestParam(required = false) Boolean promotional,
            @RequestParam(required = false) String sortBy) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getStoreProducts(promotional, sortBy));
    }

    @Secured({"Manager", "Cashier"})
    @GetMapping("/search/{upc}")
    public ResponseEntity<StoreProductInfo> getStoreProductInfo(@PathVariable String upc) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getStoreProductInfo(upc));
    }
}
