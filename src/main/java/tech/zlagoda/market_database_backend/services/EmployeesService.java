package tech.zlagoda.market_database_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.zlagoda.market_database_backend.pojos.Employee;
import tech.zlagoda.market_database_backend.pojos.UserInfoDetails;
import tech.zlagoda.market_database_backend.repositories.EmployeesRepository;

import java.util.List;

import static tech.zlagoda.market_database_backend.validators.EmployeeValidator.validate;

@Service
public class EmployeesService implements UserDetailsService {
    @Autowired
    EmployeesService(EmployeesRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    private final EmployeesRepository repository;
    private final PasswordEncoder encoder;

    public String addEmployee(Employee employee){
        validate(employee, true);
        employee.setPassword(encoder.encode(employee.getPassword()));
        repository.addEmployee(employee);
        return employee.getIdEmployee();
    }

    public String deleteEmployee(String idEmployee){
        repository.deleteEmployee(idEmployee);
        return idEmployee;
    }

    public String updateEmployee(Employee employee, String idEmployee){
        validate(employee, false);
        employee.setIdEmployee(idEmployee);
        repository.updateEmployee(employee);
        if(employee.getPassword() != null){
            repository.updatePassword(idEmployee, encoder.encode(employee.getPassword()));
        }
        return employee.getIdEmployee();
    }

    public List<Employee> getEmployees(String surname, String role){
        return repository.getEmployees(surname, role);
    }

    public Employee getMe(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String idEmployee = authentication.getName();
        return repository.getEmployee(idEmployee);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = repository.getEmployee(username);
        if(employee == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new UserInfoDetails(employee);
    }
}
