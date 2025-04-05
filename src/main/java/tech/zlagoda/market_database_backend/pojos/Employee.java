package tech.zlagoda.market_database_backend.pojos;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.Date;

public class Employee {
    private String idEmployee;
    private String surname;
    private String name;
    private String patronymic;
    private String role;
    private BigDecimal salary;
    private Date dateOfBirth;
    private Date dateOfStart;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipCode;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;
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

    public static RowMapper<Employee> getRowMapper() {
        return (r, i) -> {
            Employee employee = new Employee();
            employee.setIdEmployee(r.getString("id_employee"));
            employee.setSurname(r.getString("empl_surname"));
            employee.setName(r.getString("empl_name"));
            employee.setPatronymic(r.getString("empl_patronymic"));
            employee.setRole(r.getString("empl_role"));
            employee.setSalary(r.getBigDecimal("salary"));
            employee.setDateOfBirth(r.getDate("date_of_birth"));
            employee.setDateOfStart(r.getDate("date_of_start"));
            employee.setPhoneNumber(r.getString("phone_number"));
            employee.setCity(r.getString("city"));
            employee.setStreet(r.getString("street"));
            employee.setZipCode(r.getString("zip_code"));
            return employee;
        };
    }
}
