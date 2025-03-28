package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.Category;
import tech.zlagoda.market_database_backend.repositories.CategoriesRepository;
import tech.zlagoda.market_database_backend.security.ManagerCheck;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    CategoriesController(CategoriesRepository repository) {
        this.repository = repository;
    }

    private final CategoriesRepository repository;

    @ManagerCheck
    @PostMapping
    public ResponseEntity<Integer> addCategory(@RequestBody Category category) {
        repository.addCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(category.getCategoryNumber());
    }

    @ManagerCheck
    @DeleteMapping("/{categoryNumber}")
    public ResponseEntity<Integer> deleteCategory(@PathVariable int categoryNumber) {
        repository.deleteCategory(categoryNumber);
        return ResponseEntity.status(HttpStatus.OK).body(categoryNumber);
    }

    @ManagerCheck
    @PutMapping("/{categoryNumber}")
    public ResponseEntity<Integer> updateCategory(@RequestBody Category category, @PathVariable int categoryNumber) {
        category.setCategoryNumber(categoryNumber);
        repository.updateCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(category.getCategoryNumber());
    }

    @ManagerCheck
    @GetMapping("/search")
    public ResponseEntity<List<Category>> getCategories(@RequestParam(required = false) String categoryName){
        return ResponseEntity.status(HttpStatus.OK).body(repository.getCategories(categoryName));
    }
}
