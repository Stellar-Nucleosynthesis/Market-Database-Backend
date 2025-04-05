package tech.zlagoda.market_database_backend.pojos;

import org.springframework.jdbc.core.RowMapper;

public class Product {
    private int idProduct;
    private int categoryNumber;
    private String productName;
    private String manufacturer;
    private String characteristics;

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(int categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public static RowMapper<Product> getRowMapper() {
        return (r, i) -> {
            Product product = new Product();
            product.setIdProduct(r.getInt("id_product"));
            product.setCategoryNumber(r.getInt("category_number"));
            product.setProductName(r.getString("product_name"));
            product.setManufacturer(r.getString("manufacturer"));
            product.setCharacteristics(r.getString("characteristics"));
            return product;
        };
    }
}
