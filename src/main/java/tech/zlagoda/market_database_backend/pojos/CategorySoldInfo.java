package tech.zlagoda.market_database_backend.pojos;

import org.springframework.jdbc.core.RowMapper;

public class CategorySoldInfo {
    private int categoryNumber;
    private String categoryName;

    private int totalSold;

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

    public int getTotalSold() { return totalSold; }

    public void setTotalSold(int totalSold) { this.totalSold = totalSold; }

    public static RowMapper<CategorySoldInfo> getRowMapper() {
        return (r, i) -> {
            CategorySoldInfo info = new CategorySoldInfo();
            info.setCategoryNumber(r.getInt("category_number"));
            info.setCategoryName(r.getString("category_name"));
            info.setTotalSold(r.getInt("total_sold"));
            return info;
        };
    }
}
