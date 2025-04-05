package tech.zlagoda.market_database_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tech.zlagoda.market_database_backend.pojos.Sale;

import java.util.List;

@Repository
public class SalesRepository {
    @Autowired
    public SalesRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final JdbcTemplate jdbc;

    public void addSale(Sale sale){
        String sql = "INSERT INTO Sale (UPC, receipt_number, product_number, selling_price) VALUES (?, ?, ?, ?);";
        jdbc.update(sql, sale.getUPC(), sale.getReceiptNumber(), sale.getProductNumber(), sale.getSellingPrice());
    }

    public void deleteSales(String receiptNumber){
        String sql = "DELETE FROM Sale WHERE receipt_number = ?;";
        jdbc.update(sql, receiptNumber);
    }

    public List<Sale> getSales(String receiptNumber){
        RowMapper<Sale> receiptRowMapper = Sale.getRowMapper();
        String sql = "SELECT * FROM Sale WHERE receipt_number = ?;";
        return jdbc.query(sql, receiptRowMapper, receiptNumber);
    }
}
