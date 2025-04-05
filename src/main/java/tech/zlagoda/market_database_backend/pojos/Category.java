package tech.zlagoda.market_database_backend.pojos;

import org.springframework.jdbc.core.RowMapper;

public class Category {
    private int categoryNumber;
    private String categoryName;

    public int getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(int categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public static RowMapper<Category> getRowMapper() {
        return (r, i) -> {
            Category category = new Category();
            category.setCategoryNumber(r.getInt("category_number"));
            category.setCategoryName(r.getString("category_name"));
            return category;
        };
    }
}
