package tech.zlagoda.market_database_backend.validators;

import tech.zlagoda.market_database_backend.pojos.StoreProduct;

import java.math.BigDecimal;

public class StoreProductValidator {
    public static void validate(StoreProduct storeProduct) {
        if (storeProduct == null) {
            throw new IllegalArgumentException("Illegal store product information");
        }
        String upc = storeProduct.getUpc();
        if (upc == null || upc.length() > 12 || upc.isEmpty()) {
            throw new IllegalArgumentException("Illegal store product UPC format");
        }
        String upcProm = storeProduct.getUpcProm();
        if (upcProm != null && (upcProm.length() > 12 || upcProm.isEmpty())) {
            throw new IllegalArgumentException("Illegal store product UPC prom format");
        }
        if (storeProduct.getIdProduct() < 0) {
            throw new IllegalArgumentException("Illegal store product ID");
        }
        BigDecimal price = storeProduct.getSellingPrice();
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Illegal store product selling price");
        }
        if (storeProduct.getProductsNumber() < 0) {
            throw new IllegalArgumentException("Illegal number of store products");
        }
    }
}
