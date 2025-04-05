package tech.zlagoda.market_database_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tech.zlagoda.market_database_backend.pojos.Employee;
import tech.zlagoda.market_database_backend.repositories.EmployeesRepository;
import tech.zlagoda.market_database_backend.repositories.UserInfoRepository;

import java.util.List;

import static tech.zlagoda.market_database_backend.validators.EmployeeValidator.validate;

@Service
public class EmployeesService {
    @Autowired
    EmployeesService(EmployeesRepository employeesRepository, UserInfoRepository userInfoRepository) {
        this.employeesRepository = employeesRepository;
        this.userInfoRepository = userInfoRepository;
    }

    private final EmployeesRepository employeesRepository;
    private final UserInfoRepository userInfoRepository;

    public String addEmployee(Employee employee){
        validate(employee);
        employeesRepository.addEmployee(employee);
        return employee.getIdEmployee();
    }

    public String deleteEmployee(String idEmployee){
        employeesRepository.deleteEmployee(idEmployee);
        return idEmployee;
    }

    public String updateEmployee(Employee employee, String idEmployee){
        validate(employee);
        employee.setIdEmployee(idEmployee);
        employeesRepository.updateEmployee(employee);
        return employee.getIdEmployee();
    }

    public List<Employee> getEmployees(String surname, String role){
        return employeesRepository.getEmployees(surname, role);
    }

    public Employee getEmployee(String idEmployee){
        return employeesRepository.getEmployee(idEmployee);
    }

    public Employee getMe(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userInfoRepository.getUserInfo(username).getEmployee();
    }
}
