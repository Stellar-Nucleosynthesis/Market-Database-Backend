package tech.zlagoda.market_database_backend.pojos;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;

public class Sale {
    private String UPC;
    private String receiptNumber;
    private int productNumber;
    private BigDecimal sellingPrice;

    public String getUPC() {
        return UPC;
    }

    public void setUPC(String UPC) {
        this.UPC = UPC;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public static RowMapper<Sale> getRowMapper() {
        return (r, i) -> {
            Sale sale = new Sale();
            sale.setUPC(r.getString("UPC"));
            sale.setReceiptNumber(r.getString("receipt_number"));
            sale.setProductNumber(r.getInt("product_number"));
            sale.setSellingPrice(r.getBigDecimal("selling_price"));
            return sale;
        };
    }
}
