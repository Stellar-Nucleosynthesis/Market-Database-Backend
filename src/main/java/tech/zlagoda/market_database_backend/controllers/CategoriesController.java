package tech.zlagoda.market_database_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import tech.zlagoda.market_database_backend.pojos.Category;
import tech.zlagoda.market_database_backend.pojos.RequestResponse;
import tech.zlagoda.market_database_backend.services.CategoriesService;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    CategoriesController(CategoriesService service) {
        this.service = service;
    }

    private final CategoriesService service;

    @Secured("Manager")
    @PostMapping
    public ResponseEntity<RequestResponse> addCategory(@RequestBody Category category) {
        String id = String.valueOf(service.addCategory(category));
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse(id, true, null));
    }

    @Secured("Manager")
    @DeleteMapping("/{categoryNumber}")
    public ResponseEntity<RequestResponse> deleteCategory(@PathVariable int categoryNumber) {
        String id = String.valueOf(service.deleteCategory(categoryNumber));
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse(id, true, null));
    }

    @Secured("Manager")
    @PutMapping("/{categoryNumber}")
    public ResponseEntity<RequestResponse> updateCategory(@RequestBody Category category, @PathVariable int categoryNumber) {
        String id = String.valueOf(service.updateCategory(category, categoryNumber));
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse(id, true, null));
    }

    @Secured({"Cashier", "Manager"})
    @GetMapping("/search")
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getCategories());
    }
}
