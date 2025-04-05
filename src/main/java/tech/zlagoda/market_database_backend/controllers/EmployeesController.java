package tech.zlagoda.market_database_backend.controllers;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.Employee;
import tech.zlagoda.market_database_backend.repositories.EmployeesRepository;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    EmployeesController(EmployeesRepository repository) {
        this.repository = repository;
    }

    private final EmployeesRepository repository;

    @RolesAllowed({"Manager"})
    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee){
        repository.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body(employee.getIdEmployee());
    }

    @RolesAllowed({"Manager"})
    @DeleteMapping("/{idEmployee}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String idEmployee){
        repository.deleteEmployee(idEmployee);
        return ResponseEntity.status(HttpStatus.OK).body(idEmployee);
    }

    @RolesAllowed({"Manager"})
    @PutMapping("/{idEmployee}")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee, @PathVariable String idEmployee){
        employee.setIdEmployee(idEmployee);
        repository.updateEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body(employee.getIdEmployee());
    }

    @RolesAllowed({"Manager"})
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> getEmployees(
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String role){
        return ResponseEntity.status(HttpStatus.OK).body(repository.getEmployees(surname, role));
    }

    @RolesAllowed({"Manager", "Cashier"})
    @GetMapping("/me")
    public ResponseEntity<Employee> getMe(){
        throw new UnsupportedOperationException("Unable to get information about \"me\"");
    }
}
