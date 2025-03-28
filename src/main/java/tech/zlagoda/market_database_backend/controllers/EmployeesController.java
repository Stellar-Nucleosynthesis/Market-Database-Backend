package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.Employee;
import tech.zlagoda.market_database_backend.repositories.EmployeesRepository;
import tech.zlagoda.market_database_backend.security.ManagerCheck;

import java.util.List;

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

    @ManagerCheck
    @DeleteMapping("/{idEmployee}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String idEmployee){
        repository.deleteEmployee(idEmployee);
        return ResponseEntity.status(HttpStatus.OK).body(idEmployee);
    }

    @ManagerCheck
    @PutMapping("/{idEmployee}")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee, @PathVariable String idEmployee){
        employee.setIdEmployee(idEmployee);
        repository.updateEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body(employee.getIdEmployee());
    }

    @ManagerCheck
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> getEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname){
        return ResponseEntity.status(HttpStatus.OK).body(repository.getEmployees(name, surname));
    }

    @ManagerCheck
    @GetMapping("/search/me")
    public ResponseEntity<Employee> getMe(){
        throw new UnsupportedOperationException("Unable to get information about \"me\"");
    }
}
