package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.Category;
import tech.zlagoda.market_database_backend.repositories.CategoriesRepository;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    CategoriesController(CategoriesRepository repository) {
        this.repository = repository;
    }

    private final CategoriesRepository repository;

    @Secured("Manager")
    @PostMapping
    public ResponseEntity<Integer> addCategory(@RequestBody Category category) {
        repository.addCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(category.getCategoryNumber());
    }

    @Secured("Manager")
    @DeleteMapping("/{categoryNumber}")
    public ResponseEntity<Integer> deleteCategory(@PathVariable int categoryNumber) {
        repository.deleteCategory(categoryNumber);
        return ResponseEntity.status(HttpStatus.OK).body(categoryNumber);
    }

    @Secured("Manager")
    @PutMapping("/{categoryNumber}")
    public ResponseEntity<Integer> updateCategory(@RequestBody Category category, @PathVariable int categoryNumber) {
        category.setCategoryNumber(categoryNumber);
        repository.updateCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(category.getCategoryNumber());
    }

    @Secured("Manager")
    @GetMapping("/search")
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(repository.getCategories());
    }
}
