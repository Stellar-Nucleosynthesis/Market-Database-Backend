package tech.zlagoda.market_database_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tech.zlagoda.market_database_backend.controllers.StoreProductsController;
import tech.zlagoda.market_database_backend.pojos.StoreProduct;

import java.math.BigDecimal;
import java.util.List;

import static tech.zlagoda.market_database_backend.validators.StoreProductValidator.validate;

@Repository
public class StoreProductsRepository {
    private final JdbcTemplate jdbc;

    @Autowired
    StoreProductsRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

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

    public List<StoreProduct> getStoreProducts(String upc, Boolean promotional) {
        RowMapper<StoreProduct> rowMapper = (rs, rowNum) -> {
            StoreProduct storeProduct = new StoreProduct();
            storeProduct.setUpc(rs.getString("UPC"));
            storeProduct.setUpcProm(rs.getString("UPC_prom"));
            storeProduct.setIdProduct(rs.getInt("id_product"));
            storeProduct.setSellingPrice(rs.getBigDecimal("selling_price"));
            storeProduct.setProductsNumber(rs.getInt("products_number"));
            storeProduct.setPromotionalProduct(rs.getBoolean("promotional_product"));
            return storeProduct;
        };
        String sql = "SELECT * FROM Store_Product";
        if(upc != null && promotional != null) {
            sql += " WHERE UPC = ? AND promotional_product = ?";
        } else if (upc != null) {
            sql += " WHERE UPC = ?";
            return jdbc.query(sql, rowMapper, upc);
        } else if (promotional != null) {
            sql += " WHERE promotional_product = ?";
            return jdbc.query(sql, rowMapper, promotional);
        }
        return jdbc.query(sql, rowMapper);
    }
}