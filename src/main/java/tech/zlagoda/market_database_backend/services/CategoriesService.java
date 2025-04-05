package tech.zlagoda.market_database_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.zlagoda.market_database_backend.pojos.Category;
import tech.zlagoda.market_database_backend.repositories.CategoriesRepository;

import java.util.List;

import static tech.zlagoda.market_database_backend.validators.CategoryValidator.validate;

@Service
public class CategoriesService {
    @Autowired
    CategoriesService(CategoriesRepository repository) {
        this.repository = repository;
    }

    private final CategoriesRepository repository;

    public Integer addCategory(Category category) {
        validate(category);
        repository.addCategory(category);
        return category.getCategoryNumber();
    }

    public Integer deleteCategory(int categoryNumber) {
        repository.deleteCategory(categoryNumber);
        return categoryNumber;
    }

    public Integer updateCategory(Category category, int categoryNumber) {
        validate(category);
        category.setCategoryNumber(categoryNumber);
        repository.updateCategory(category);
        return category.getCategoryNumber();
    }

    public List<Category> getCategories() {
        return repository.getCategories();
    }
}
