package tech.zlagoda.market_database_backend.pojos;

import org.springframework.jdbc.core.RowMapper;

public class UserInfo {
    private String username;
    private String password;
    private Employee employee;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public static RowMapper<UserInfo> getRowMapper() {
        return (r, i) -> {
            UserInfo info = new UserInfo();
            Employee employee = new Employee();
            info.setEmployee(employee);
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
            info.setUsername(r.getString("username"));
            info.setPassword(r.getString("password"));
            return info;
        };
    }
}
