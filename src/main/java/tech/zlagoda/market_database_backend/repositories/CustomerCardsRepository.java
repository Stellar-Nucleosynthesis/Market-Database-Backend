package tech.zlagoda.market_database_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tech.zlagoda.market_database_backend.pojos.CustomerCard;

import java.util.List;

import static tech.zlagoda.market_database_backend.validators.CustomerCardValidator.validate;

@Repository
public class CustomerCardsRepository {
    @Autowired
    CustomerCardsRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final JdbcTemplate jdbc;

    public void addCustomerCard(CustomerCard cc) {
        validate(cc);
        String sql = "INSERT INTO Customer_Card (card_number, cust_surname, cust_name, cust_patronymic, phone_number, " +
                "city, street, zip_code, percent) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        jdbc.update(sql, cc.getCardNumber(), cc.getSurname(), cc.getName(), cc.getPatronymic(), cc.getPhoneNumber(),
                cc.getCity(), cc.getStreet(), cc.getZipCode(), cc.getPercent());
    }

    public void deleteCustomerCard(String cardNumber) {
        String sql = "DELETE FROM Customer_Card WHERE card_number = ?;";
        jdbc.update(sql, cardNumber);
    }

    public void updateCustomerCard(CustomerCard cc) {
        validate(cc);
        String sql = "UPDATE Customer_Card SET cust_surname = ?, cust_name = ?, cust_patronymic = ?, phone_number = ?, " +
                "city = ?, street = ?, zip_code = ?, percent = ? WHERE card_number = ?;";
        jdbc.update(sql, cc.getSurname(), cc.getName(), cc.getPatronymic(), cc.getPhoneNumber(),
                cc.getCity(), cc.getStreet(), cc.getZipCode(), cc.getPercent(), cc.getCardNumber());
    }

    public List<CustomerCard> getCustomerCards(String surname, Integer percent) {
        RowMapper<CustomerCard> customerCardRowMapper = CustomerCard.getRowMapper();
        String sql = "SELECT * FROM Customer_Card";
        if (surname != null && percent != null) {
            sql += " WHERE cust_surname = ? AND percent = ? " +
                    "ORDER BY cust_name ASC;";
            return jdbc.query(sql, customerCardRowMapper, surname, percent);
        } else if (surname != null) {
            sql += " WHERE cust_surname = ? " +
                    "ORDER BY cust_name ASC;";
            return jdbc.query(sql, customerCardRowMapper, surname);
        } else if (percent != null) {
            sql += " WHERE percent = ? " +
                    "ORDER BY percent ASC;";
            return jdbc.query(sql, customerCardRowMapper, percent);
        }
        sql += " ORDER BY cust_surname ASC;";
        return jdbc.query(sql, customerCardRowMapper);
    }
}