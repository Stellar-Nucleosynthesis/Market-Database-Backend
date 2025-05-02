package tech.zlagoda.market_database_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tech.zlagoda.market_database_backend.pojos.*;

import java.sql.Date;
import java.util.List;

@Repository
public class SpecificInfoRepository {
    @Autowired
    public SpecificInfoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final JdbcTemplate jdbc;

    public List<CategorySoldInfo> getNumSoldByCategory(String idEmployee, Date from, Date to) {
        RowMapper<CategorySoldInfo> rm = CategorySoldInfo.getRowMapper();
        String sql = "SELECT c.category_number, c.category_name, " +
                "    SUM(s.product_number) AS total_sold" +
                " FROM " +
                "    Sale s" +
                " JOIN " +
                "    Receipt r ON s.receipt_number = r.receipt_number" +
                " JOIN " +
                "    Store_Product sp ON s.UPC = sp.UPC" +
                " JOIN " +
                "    Product p ON sp.id_product = p.id_product" +
                " JOIN " +
                "    Category c ON c.category_number = p.category_number" +
                " WHERE r.id_employee = ?" +
                "    AND r.print_date BETWEEN ? AND ?" +
                " GROUP BY " +
                "    c.category_number" +
                " ORDER BY " +
                "    total_sold DESC;";
        return jdbc.query(sql, rm, idEmployee, from, to);
    }

    public List<Employee> getNoDiscountSalesEmployees() {
        RowMapper<Employee> rm = Employee.getRowMapper();
        String sql = "SELECT * " +
                "FROM " +
                "    Employee e " +
                "WHERE " +
                "    NOT EXISTS (" +
                "        SELECT *" +
                "        FROM Sale s" +
                "        WHERE EXISTS (" +
                "            SELECT *" +
                "            FROM Receipt r " +
                "            WHERE r.receipt_number = s.receipt_number " +
                "            AND r.id_employee = e.id_employee" +
                "        )" +
                "        AND EXISTS (" +
                "            SELECT *" +
                "            FROM Store_product sp " +
                "            WHERE sp.UPC = s.UPC " +
                "            AND sp.promotional_product = true" +
                "        )" +
                "    )" +
                "    AND NOT EXISTS (" +
                "        SELECT *" +
                "        FROM Receipt r " +
                "        WHERE r.id_employee = e.id_employee " +
                "        AND r.receipt_number IS NULL" +
                "    );";
        return jdbc.query(sql, rm);
    }

    public List<EmployeeAverageSaleInfo> getAverageCashierSale() {
        RowMapper<EmployeeAverageSaleInfo> rm = EmployeeAverageSaleInfo.getRowMapper();
        String sql = "SELECT *, AVG(s.selling_price) AS average_sale" +
                    " FROM Employee e" +
                    " JOIN Receipt r ON e.id_employee = r.id_employee" +
                    " JOIN Sale s ON r.receipt_number = s.receipt_number" +
                    " GROUP BY e.id_employee;";
        return jdbc.query(sql, rm);
    }

    public List<StoreProductInfo> getUnsoldWithNoDiscount(Date from){
        RowMapper<StoreProductInfo> rm = StoreProductInfo.getRowMapper();
        String sql = "SELECT DISTINCT sp.selling_price, sp.products_number, " +
                    " p.product_name, p.manufacturer, p.characteristics" +
                    " FROM Store_Product sp" +
                    " JOIN Product p ON sp.id_product = p.id_product" +
                    " WHERE sp.promotional_product = 0 AND NOT EXISTS (" +
                    "       SELECT *" +
                    "       FROM Sale s" +
                    "       WHERE s.UPC = sp.UPC AND NOT EXISTS (" +
                    "              SELECT *" +
                    "              FROM Receipt r" +
                    "              WHERE r.receipt_number = s.receipt_number AND r.print_date > ?" +
                    "               )" +
                    " );";
        return jdbc.query(sql, rm, from);
    }
}
