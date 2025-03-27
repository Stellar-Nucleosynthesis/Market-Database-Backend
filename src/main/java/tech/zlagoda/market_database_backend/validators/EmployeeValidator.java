package tech.zlagoda.market_database_backend.validators;

import tech.zlagoda.market_database_backend.pojos.Employee;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class EmployeeValidator {
    public static void validate(Employee employee) {
        if(employee == null) {
            throw new IllegalArgumentException("Illegal employee information");
        }
        String id = employee.getIdEmployee();
        if(id == null || id.isEmpty() || id.length() > 10) {
            throw new IllegalArgumentException("Illegal employee ID");
        }
        String name = employee.getName();
        if(name == null || name.isEmpty() || name.length() > 50) {
            throw new IllegalArgumentException("Illegal employee name");
        }
        String surName = employee.getSurname();
        if(surName == null || surName.isEmpty() || surName.length() > 50) {
            throw new IllegalArgumentException("Illegal employee surname");
        }
        String patronymic = employee.getPatronymic();
        if(patronymic != null && patronymic.length() > 50) {
            throw new IllegalArgumentException("Illegal employee patronymic");
        }
        String role = employee.getRole();
        if(role == null || (!role.equals("MANAGER") && role.equals("CASHIER"))) {
            throw new IllegalArgumentException("Illegal employee role");
        }
        BigDecimal salary = employee.getSalary();
        if((salary == null) || salary.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Illegal employee salary");
        }
        Date birth = employee.getDateOfBirth();
        if(birth == null) {
            throw new IllegalArgumentException("Illegal employee date of birth");
        }
        LocalDate today = LocalDate.now();
        if(birth.toLocalDate().plusYears(18).isAfter(today)) {
            throw new IllegalArgumentException("Illegal employee age");
        }
        Date start = employee.getDateOfStart();
        if(start == null) {
            throw new IllegalArgumentException("Illegal employee date of start");
        }
        String phoneNumber = employee.getPhoneNumber();
        if(phoneNumber == null || phoneNumber.length() > 13) {
            throw new IllegalArgumentException("Illegal employee phone number");
        }
        String city = employee.getCity();
        if(city == null || city.isEmpty() || city.length() > 50) {
            throw new IllegalArgumentException("Illegal employee city");
        }
        String street = employee.getStreet();
        if(street == null || street.isEmpty() || street.length() > 50) {
            throw new IllegalArgumentException("Illegal employee street");
        }
        String zipCode = employee.getZipCode();
        if(zipCode == null || zipCode.isEmpty() || zipCode.length() > 9) {
            throw new IllegalArgumentException("Illegal employee zipcode");
        }
    }
}
