package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.server.servlet.OAuth2AuthorizationServerProperties.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.zlagoda.market_database_backend.pojos.Employee;
import tech.zlagoda.market_database_backend.repositories.EmployeesRepository;
import tech.zlagoda.market_database_backend.security.ManagerCheck;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    EmployeesController(EmployeesRepository repository) {
        this.repository = repository;
    }

    private final EmployeesRepository repository;

    @ManagerCheck
    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee){
        repository.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body(employee.getIdEmployee());
    }
}
