package tech.zlagoda.market_database_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tech.zlagoda.market_database_backend.pojos.Category;

import java.util.List;

import static tech.zlagoda.market_database_backend.validators.CategoryValidator.validate;

@Repository
public class CategoryRepository {
    @Autowired
    CategoryRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final JdbcTemplate jdbc;

    public void addCategory(Category category) {
        validate(category);
        String sql = "INSERT INTO Category VALUES (?, ?)";
        jdbc.update(sql,
                category.getCategoryNumber(),
                category.getCategoryName());
    }

    public void deleteCategory(int categoryNumber) {
        String sql = "DELETE FROM Category WHERE category_number = ?";
        jdbc.update(sql, categoryNumber);
    }

    public void changeCategory(Category category) {
        validate(category);
        deleteCategory(category.getCategoryNumber());
        addCategory(category);
    }

    public List<Category> getCategories(String categoryName) {
        RowMapper<Category> categoryRowMapper = (r, i) -> {
            Category category = new Category();
            category.setCategoryNumber(r.getInt("category_number"));
            category.setCategoryName(r.getString("category_name"));
            return category;
        };
        String sql = "SELECT * FROM Category";
        if (categoryName != null) {
            sql += " WHERE category_name LIKE ?";
            return jdbc.query(sql, categoryRowMapper, categoryName);
        }
        return jdbc.query(sql, categoryRowMapper);
    }
}
