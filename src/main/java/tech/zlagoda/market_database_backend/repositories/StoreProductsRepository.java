package tech.zlagoda.market_database_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tech.zlagoda.market_database_backend.pojos.StoreProduct;
import tech.zlagoda.market_database_backend.pojos.StoreProductInfo;

import java.util.ArrayList;
import java.util.List;

import static tech.zlagoda.market_database_backend.validators.StoreProductValidator.validate;

@Repository
public class StoreProductsRepository {
    @Autowired
    StoreProductsRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final JdbcTemplate jdbc;

    public void addStoreProduct(StoreProduct storeProduct) {
        validate(storeProduct);
        String sql = "INSERT INTO Store_Product (UPC, UPC_prom, id_product, " +
                "selling_price, products_number, promotional_product) VALUES (?, ?, ?, ?, ?, ?)";
        jdbc.update(sql,
                storeProduct.getUpc(),
                storeProduct.getUpcProm(),
                storeProduct.getIdProduct(),
                storeProduct.getSellingPrice(),
                storeProduct.getProductsNumber(),
                storeProduct.isPromotionalProduct());
    }

    public void deleteStoreProduct(String upc) {
        String sql = "DELETE FROM Store_Product WHERE UPC = ?";
        jdbc.update(sql, upc);
    }

    public void updateStoreProduct(StoreProduct storeProduct) {
        validate(storeProduct);
        String sql = "UPDATE Store_Product SET UPC_prom = ?, id_product = ?, selling_price = ?, " +
                "products_number = ?, promotional_product = ? WHERE UPC = ?";
        jdbc.update(sql,
                storeProduct.getUpcProm(),
                storeProduct.getIdProduct(),
                storeProduct.getSellingPrice(),
                storeProduct.getProductsNumber(),
                storeProduct.isPromotionalProduct(),
                storeProduct.getUpc());
    }

    public List<StoreProduct> getStoreProducts(Boolean promotional, String sortBy) {
        RowMapper<StoreProduct> storeProductRowMapper = (r, rowNum) -> {
            StoreProduct storeProduct = new StoreProduct();
            storeProduct.setUpc(r.getString("UPC"));
            storeProduct.setUpcProm(r.getString("UPC_prom"));
            storeProduct.setIdProduct(r.getInt("id_product"));
            storeProduct.setSellingPrice(r.getBigDecimal("selling_price"));
            storeProduct.setProductsNumber(r.getInt("products_number"));
            storeProduct.setPromotionalProduct(r.getBoolean("promotional_product"));
            return storeProduct;
        };
        String sql = "SELECT * FROM Store_Product";
        if(promotional != null) {
            sql += " WHERE promotional_product = ?";
            if ("quantity".equals(sortBy)) {
                sql += " ORDER BY products_number ASC";
            }
            return jdbc.query(sql, storeProductRowMapper, promotional);
        }
        if ("quantity".equals(sortBy)) {
            sql += " ORDER BY products_number ASC";
        }
        return jdbc.query(sql, storeProductRowMapper);
    }

    public StoreProductInfo getStoreProductInfo(String upc) {
        RowMapper<StoreProductInfo> storeProductInfoRowMapper = (r, rowNum) -> {
            StoreProductInfo storeProductInfo = new StoreProductInfo();
            storeProductInfo.setSellingPrice(r.getBigDecimal("selling_price"));
            storeProductInfo.setProductsNumber(r.getInt("products_number"));
            storeProductInfo.setProductName(r.getString("product_name"));
            storeProductInfo.setManufacturer(r.getString("manufacturer"));
            storeProductInfo.setCharacteristics(r.getString("characteristics"));
            return storeProductInfo;
        };
        String sql = "SELECT selling_price, products_number, product_name, manufacturer, characteristics" +
                    " FROM Store_Product, Product" +
                    " WHERE UPC = ?";
        return jdbc.queryForObject(sql, storeProductInfoRowMapper, upc);
    }
}