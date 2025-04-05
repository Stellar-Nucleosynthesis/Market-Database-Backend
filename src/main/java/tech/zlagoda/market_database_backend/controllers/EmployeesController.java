package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.Employee;
import tech.zlagoda.market_database_backend.services.EmployeesService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    EmployeesController(EmployeesService service) {
        this.service = service;
    }

    private final EmployeesService service;

    @Secured({"Manager"})
    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee){
        return ResponseEntity.status(HttpStatus.OK).body(service.addEmployee(employee));
    }

    @Secured({"Manager"})
    @DeleteMapping("/{idEmployee}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String idEmployee){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteEmployee(idEmployee));
    }

    @Secured({"Manager"})
    @PutMapping("/{idEmployee}")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee, @PathVariable String idEmployee){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateEmployee(employee, idEmployee));
    }

    @Secured({"Manager"})
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> getEmployees(
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String role){
        return ResponseEntity.status(HttpStatus.OK).body(service.getEmployees(surname, role));
    }

    @Secured({"Manager", "Cashier"})
    @GetMapping("/me")
    public ResponseEntity<Employee> getMe(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getMe());
    }
}
