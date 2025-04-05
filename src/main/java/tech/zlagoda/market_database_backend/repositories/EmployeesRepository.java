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
        String sql = "INSERT INTO Employee (id_employee, empl_surname, empl_name, empl_patronymic, " +
                "empl_role, salary, date_of_birth, date_of_start, phone_number, city, street, zip_code) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
        String sql = "DELETE FROM Employee WHERE id_employee = ?;";
        jdbc.update(sql, idEmployee);
    }

    public void updateEmployee(Employee employee) {
        String sql = "UPDATE Employee SET empl_surname = ?, empl_name = ?, empl_patronymic = ?, empl_role = ?, " +
                "salary = ?, date_of_birth = ?, date_of_start = ?, phone_number = ?, city = ?, street = ?, zip_code = ? " +
                "WHERE id_employee = ?;";
        jdbc.update(sql,
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
                employee.getZipCode(),
                employee.getIdEmployee());
    }

    public List<Employee> getEmployees(String surname, String role) {
        RowMapper<Employee> employeeRowMapper = Employee.getRowMapper();
        String sql = "SELECT * FROM Employee";
        if (surname != null && role != null) {
            sql += " WHERE empl_surname = ? AND empl_role = ? " +
                    "ORDER BY empl_surname ASC;";
            return jdbc.query(sql, employeeRowMapper, surname, role);
        } else if (surname != null) {
            sql += " WHERE empl_surname = ? " +
                    "ORDER BY empl_surname ASC;";
            return jdbc.query(sql, employeeRowMapper, surname);
        } else if (role != null) {
            sql += " WHERE empl_role = ? " +
                    "ORDER BY empl_surname ASC;";
            return jdbc.query(sql, employeeRowMapper, role);
        }
        sql += " ORDER BY empl_surname ASC;";
        return jdbc.query(sql, employeeRowMapper);
    }
}
