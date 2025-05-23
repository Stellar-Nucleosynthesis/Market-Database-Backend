package tech.zlagoda.market_database_backend.validators;

import tech.zlagoda.market_database_backend.pojos.Employee;

import java.sql.Date;
import java.time.LocalDate;

import static tech.zlagoda.market_database_backend.validators.ValidationUtils.*;

public class EmployeeValidator {
    public static void validate(Employee employee, boolean requirePasswd){
        validate(employee);
        if(requirePasswd || employee.getPassword() != null){
            validateString(employee.getPassword(), "employee password", 1, 100);
        }
    }

    private static void validate(Employee employee) {
        if(employee == null) {
            throw new IllegalArgumentException("Illegal employee information");
        }
        validateString(employee.getIdEmployee(), "employee ID", 1, 10);
        validateString(employee.getName(), "employee name", 1, 50);
        validateString(employee.getSurname(), "employee surname", 1, 50);
        if(employee.getPatronymic() != null){
            validateString(employee.getPatronymic(), "employee patronymic", 0, 50);
        }
        String role = employee.getRole();
        if(role == null || (!role.equals("Manager") && !role.equals("Cashier"))) {
            throw new IllegalArgumentException("Illegal employee role");
        }
        validateDecimal(employee.getSalary(), "employee salary", 0);
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
        validatePhoneNumber(employee.getPhoneNumber());
        validateString(employee.getCity(), "employee city", 1, 50);
        validateString(employee.getStreet(), "employee street", 1, 50);
        validateString(employee.getZipCode(), "employee zipcode", 1, 9);
    }
}
