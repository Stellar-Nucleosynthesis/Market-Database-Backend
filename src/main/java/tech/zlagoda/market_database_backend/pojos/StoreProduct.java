package tech.zlagoda.market_database_backend.pojos;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;

public class StoreProduct {
    private String upc;
    private String upcProm;
    private int idProduct;
    private BigDecimal sellingPrice;
    private int productsNumber;
    private boolean promotionalProduct;

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getUpcProm() {
        return upcProm;
    }

    public void setUpcProm(String upcProm) {
        this.upcProm = upcProm;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

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

    public boolean isPromotionalProduct() {
        return promotionalProduct;
    }

    public void setPromotionalProduct(boolean promotionalProduct) {
        this.promotionalProduct = promotionalProduct;
    }

    public static RowMapper<StoreProduct> getRowMapper() {
        return (r, i) -> {
            StoreProduct storeProduct = new StoreProduct();
            storeProduct.setUpc(r.getString("UPC"));
            storeProduct.setUpcProm(r.getString("UPC_prom"));
            storeProduct.setIdProduct(r.getInt("id_product"));
            storeProduct.setSellingPrice(r.getBigDecimal("selling_price"));
            storeProduct.setProductsNumber(r.getInt("products_number"));
            storeProduct.setPromotionalProduct(r.getBoolean("promotional_product"));
            return storeProduct;
        };
    }
}