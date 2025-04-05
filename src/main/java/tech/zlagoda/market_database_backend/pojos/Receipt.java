package tech.zlagoda.market_database_backend.pojos;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private String receiptNumber;
    private String idEmployee;
    private String cardNumber;
    private Date printDate;
    private BigDecimal sumTotal;
    private BigDecimal vat;

    private List<Sale> sales = new ArrayList<>();

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getPrintDate() {
        return printDate;
    }

    public void setPrintDate(Date printDate) {
        this.printDate = printDate;
    }

    public BigDecimal getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(BigDecimal sumTotal) {
        this.sumTotal = sumTotal;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public static RowMapper<Receipt> getRowMapper() {
        return (r, i) -> {
            Receipt receipt = new Receipt();
            receipt.setReceiptNumber(r.getString("receipt_number"));
            receipt.setIdEmployee(r.getString("id_employee"));
            receipt.setCardNumber(r.getString("card_number"));
            receipt.setPrintDate(r.getDate("print_date"));
            receipt.setSumTotal(r.getBigDecimal("sum_total"));
            receipt.setVat(r.getBigDecimal("vat"));
            return receipt;
        };
    }
}
