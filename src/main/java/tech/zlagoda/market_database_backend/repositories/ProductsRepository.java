package tech.zlagoda.market_database_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tech.zlagoda.market_database_backend.pojos.Product;

import java.util.List;

import static tech.zlagoda.market_database_backend.validators.ProductValidator.validate;

@Repository
public class ProductsRepository {
    @Autowired
    ProductsRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final JdbcTemplate jdbc;

    public void addProduct(Product product) {
        validate(product);
        String sql = "INSERT INTO Product VALUES (?, ?, ?, ?, ?)";
        jdbc.update(sql,
                product.getIdProduct(),
                product.getCategoryNumber(),
                product.getProductName(),
                product.getManufacturer(),
                product.getCharacteristics());
    }

    public void deleteProduct(int idProduct) {
        String sql = "DELETE FROM Product WHERE id_product = ?";
        jdbc.update(sql, idProduct);
    }

    public void changeProduct(Product product) {
        validate(product);
        deleteProduct(product.getIdProduct());
        addProduct(product);
    }

    public List<Product> getProducts(String productName, Integer categoryNumber) {
        RowMapper<Product> productRowMapper = (r, i) -> {
            Product product = new Product();
            product.setIdProduct(r.getInt("id_product"));
            product.setCategoryNumber(r.getInt("category_number"));
            product.setProductName(r.getString("product_name"));
            product.setManufacturer(r.getString("manufacturer"));
            product.setCharacteristics(r.getString("characteristics"));
            return product;
        };
        String sql = "SELECT * FROM Product";
        if (productName != null && categoryNumber != null) {
            sql += " WHERE product_name LIKE ? AND category_number = ?";
            return jdbc.query(sql, productRowMapper, productName, categoryNumber);
        } else if (productName != null) {
            sql += " WHERE product_name LIKE ?";
            return jdbc.query(sql, productRowMapper, productName);
        } else if (categoryNumber != null) {
            sql += " WHERE category_number = ?";
            return jdbc.query(sql, productRowMapper, categoryNumber);
        }
        return jdbc.query(sql, productRowMapper);
    }
}