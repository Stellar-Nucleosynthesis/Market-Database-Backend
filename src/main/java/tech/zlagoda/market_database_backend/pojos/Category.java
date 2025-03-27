package tech.zlagoda.market_database_backend.pojos;

import java.math.BigInteger;

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
}
