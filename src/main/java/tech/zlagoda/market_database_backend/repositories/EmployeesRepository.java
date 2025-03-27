package tech.zlagoda.market_database_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tech.zlagoda.market_database_backend.pojos.Employee;

import java.util.List;

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

    public void deleteEmployee(String idEmployee) {
        String sql = "DELETE FROM Employee WHERE id_employee = ?";
        jdbc.update(sql, idEmployee);
    }

    public void changeEmployee(Employee employee) {
        validate(employee);
        deleteEmployee(employee.getIdEmployee());
        addEmployee(employee);
    }

    public List<Employee> getEmployees(String name, String surname) {
        RowMapper<Employee> employeeRowMapper = (r, i) -> {
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
        String sql = "SELECT * FROM Employee";
        if (name != null && surname != null) {
            sql += " WHERE empl_name LIKE ? AND empl_surname LIKE ?";
            return jdbc.query(sql, employeeRowMapper, name, surname);
        } else if (name != null) {
            sql += " WHERE empl_name LIKE ?";
            return jdbc.query(sql, employeeRowMapper, name);
        } else if (surname != null) {
            sql += " WHERE empl_surname LIKE ?";
            return jdbc.query(sql, employeeRowMapper, surname);
        }
        return jdbc.query(sql, employeeRowMapper);
    }
}
