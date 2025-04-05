package tech.zlagoda.market_database_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tech.zlagoda.market_database_backend.pojos.Product;

import java.util.ArrayList;
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
        String sql = "INSERT INTO Product (id_product, category_number, product_name, manufacturer, " +
                "characteristics) VALUES (?, ?, ?, ?, ?);";
        jdbc.update(sql,
                product.getIdProduct(),
                product.getCategoryNumber(),
                product.getProductName(),
                product.getManufacturer(),
                product.getCharacteristics());
    }

    public void deleteProduct(int idProduct) {
        String sql = "DELETE FROM Product WHERE id_product = ?;";
        jdbc.update(sql, idProduct);
    }

    public void updateProduct(Product product) {
        validate(product);
        String sql = "UPDATE Product SET category_number = ?, product_name = ?, manufacturer = ?, " +
                "characteristics = ? WHERE id_product = ?;";
        jdbc.update(sql,
                product.getCategoryNumber(),
                product.getProductName(),
                product.getManufacturer(),
                product.getCharacteristics(),
                product.getIdProduct());
    }

    public List<Product> getProducts(String productName, Integer categoryNumber, String sortBy) {
        RowMapper<Product> productRowMapper = Product.getRowMapper();
        String sql = "SELECT * FROM Product";
        List<Object> params = new ArrayList<>();
        if (productName != null || categoryNumber != null) {
            sql += " WHERE ";
            List<String> conditions = new ArrayList<>();
            if (productName != null) {
                conditions.add("product_name = ?");
                params.add(productName);
            }
            if (categoryNumber != null) {
                conditions.add("category_number = ?");
                params.add(categoryNumber);
            }
            sql += String.join(" AND ", conditions);
        }
        if ("name".equals(sortBy)) {
            sql += " ORDER BY product_name ASC";
        }
        sql += ";";
        return jdbc.query(sql, productRowMapper, params.toArray());
    }
}