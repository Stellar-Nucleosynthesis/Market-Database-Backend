package tech.zlagoda.market_database_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tech.zlagoda.market_database_backend.pojos.Receipt;

import java.sql.Date;
import java.util.List;

import static tech.zlagoda.market_database_backend.validators.ReceiptValidator.validate;

@Repository
public class ReceiptsRepository {
    @Autowired
    public ReceiptsRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final JdbcTemplate jdbc;

    public void addReceipt(Receipt receipt) {
        validate(receipt);
        String sql = "INSERT INTO Receipt (receipt_number, id_employee, card_number, print_date, sum_total, vat) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbc.update(sql,
                receipt.getReceiptNumber(),
                receipt.getIdEmployee(),
                receipt.getCardNumber(),
                receipt.getPrintDate(),
                receipt.getSumTotal(),
                receipt.getVat());
    }

    public void deleteReceipt(String receiptNumber) {
        String sql = "DELETE FROM Receipt WHERE receipt_number = ?";
        jdbc.update(sql, receiptNumber);
    }
}
