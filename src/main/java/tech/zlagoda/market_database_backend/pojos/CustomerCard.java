package tech.zlagoda.market_database_backend.pojos;

import org.springframework.jdbc.core.RowMapper;

public class CustomerCard {
    private String cardNumber;
    private String surname;
    private String name;
    private String patronymic;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipCode;
    private int percent;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public static RowMapper<CustomerCard> getRowMapper() {
        return (r, i) -> {
            CustomerCard cc = new CustomerCard();
            cc.setCardNumber(r.getString("card_number"));
            cc.setSurname(r.getString("cust_surname"));
            cc.setName(r.getString("cust_name"));
            cc.setPatronymic(r.getString("cust_patronymic"));
            cc.setPhoneNumber(r.getString("phone_number"));
            cc.setCity(r.getString("city"));
            cc.setStreet(r.getString("street"));
            cc.setZipCode(r.getString("zip_code"));
            cc.setPercent(r.getInt("percent"));
            return cc;
        };
    }
}
