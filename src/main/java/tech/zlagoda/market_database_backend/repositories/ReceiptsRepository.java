package tech.zlagoda.market_database_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tech.zlagoda.market_database_backend.pojos.Receipt;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReceiptsRepository {
    @Autowired
    public ReceiptsRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final JdbcTemplate jdbc;

    public void addReceipt(Receipt receipt) {
        String sql = "INSERT INTO Receipt (receipt_number, id_employee, card_number, print_date, sum_total, vat) " +
                "VALUES (?, ?, ?, ?, ?, ?);";
        jdbc.update(sql,
                receipt.getReceiptNumber(),
                receipt.getIdEmployee(),
                receipt.getCardNumber(),
                receipt.getPrintDate(),
                receipt.getSumTotal(),
                receipt.getVat());
    }

    public void deleteReceipt(String receiptNumber) {
        String sql = "DELETE FROM Receipt WHERE receipt_number = ?;";
        jdbc.update(sql, receiptNumber);
    }

    public List<Receipt> getReceipts(String idEmployee, Date from, Date to) {
        RowMapper<Receipt> receiptRowMapper = Receipt.getRowMapper();
        String sql = "SELECT * FROM Receipt";
        List<Object> params = new ArrayList<>();
        if (idEmployee != null || from != null || to != null) {
            sql += " WHERE ";
            List<String> conditions = new ArrayList<>();
            if (idEmployee != null) {
                conditions.add("id_employee = ?");
                params.add(idEmployee);
            }
            if (from != null) {
                conditions.add("print_date > ?");
                params.add(from);
            }
            if (to != null) {
                conditions.add("print_date < ?");
                params.add(to);
            }
            sql += String.join(" AND ", conditions);
        }
        sql += " ORDER BY receipt_number;";
        return jdbc.query(sql, receiptRowMapper, params.toArray());
    }

    public Receipt getReceipt(String receiptNumber) {
        RowMapper<Receipt> receiptRowMapper = Receipt.getRowMapper();
        String sql = "SELECT * FROM Receipt WHERE receipt_number = ?;";
        return jdbc.queryForObject(sql, receiptRowMapper, receiptNumber);
    }
}
