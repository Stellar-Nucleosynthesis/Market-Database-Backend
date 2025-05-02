package tech.zlagoda.market_database_backend.pojos;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;

public class StoreProductInfo {
    private BigDecimal sellingPrice;
    private int productsNumber;
    private String upc;
    private String productName;
    private String manufacturer;
    private String characteristics;

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getProductsNumber() {
        return productsNumber;
    }

    public void setProductsNumber(int productsNumber) {
        this.productsNumber = productsNumber;
    }

    public String getProductName() {
        return productName;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public static RowMapper<StoreProductInfo> getRowMapper() {
        return (r, i) -> {
            StoreProductInfo storeProductInfo = new StoreProductInfo();
            storeProductInfo.setSellingPrice(r.getBigDecimal("selling_price"));
            storeProductInfo.setProductsNumber(r.getInt("products_number"));
            storeProductInfo.setUpc(r.getString("upc"));
            storeProductInfo.setProductName(r.getString("product_name"));
            storeProductInfo.setManufacturer(r.getString("manufacturer"));
            storeProductInfo.setCharacteristics(r.getString("characteristics"));
            return storeProductInfo;
        };
    }
}
