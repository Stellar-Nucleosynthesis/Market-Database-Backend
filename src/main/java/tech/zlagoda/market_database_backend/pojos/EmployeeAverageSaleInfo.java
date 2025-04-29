package tech.zlagoda.market_database_backend.pojos;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;

public class EmployeeAverageSaleInfo {
    private String idEmployee;
    private String surname;
    private String name;
    private String patronymic;

    private BigDecimal averageSale;

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
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

    public BigDecimal getAverageSale() { return averageSale; }

    public void setAverageSale(BigDecimal averageSale) { this.averageSale = averageSale; }

    public static RowMapper<EmployeeAverageSaleInfo> getRowMapper() {
        return (r, i) -> {
            EmployeeAverageSaleInfo info = new EmployeeAverageSaleInfo();
            info.setIdEmployee(r.getString("id_employee"));
            info.setSurname(r.getString("empl_surname"));
            info.setName(r.getString("empl_name"));
            info.setPatronymic(r.getString("empl_patronymic"));
            info.setAverageSale(r.getBigDecimal("average_sale"));
            return info;
        };
    }
}
