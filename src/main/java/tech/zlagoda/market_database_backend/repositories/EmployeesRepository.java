package tech.zlagoda.market_database_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tech.zlagoda.market_database_backend.pojos.Employee;

import static tech.zlagoda.market_database_backend.validators.EmployeeValidator.validate;

@Repository
public class EmployeesRepository {
    @Autowired
    EmployeesRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final JdbcTemplate jdbc;

    public void addEmployee(Employee employee) {
        validate(employee);
        String sql = "INSERT INTO Employee VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println(employee.toString());
        jdbc.update(sql,
                employee.getIdEmployee(),
                employee.getSurname(),
                employee.getName(),
                employee.getPatronymic(),
                employee.getRole(),
                employee.getSalary(),
                employee.getDateOfBirth(),
                employee.getDateOfStart(),
                employee.getPhoneNumber(),
                employee.getCity(),
                employee.getStreet(),
                employee.getZipCode());
    }
}
