package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.Employee;
import tech.zlagoda.market_database_backend.pojos.RequestResponse;
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
    public ResponseEntity<RequestResponse> addEmployee(@RequestBody Employee employee) {
        String id = service.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse(id, true, null));
    }

    @Secured({"Manager"})
    @DeleteMapping("/{idEmployee}")
    public ResponseEntity<RequestResponse> deleteEmployee(@PathVariable String idEmployee){
        String id = service.deleteEmployee(idEmployee);
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse(id, true, null));
    }

    @Secured({"Manager"})
    @PutMapping("/{idEmployee}")
    public ResponseEntity<RequestResponse> updateEmployee(
            @RequestBody Employee employee,
            @PathVariable String idEmployee){
        String id = service.updateEmployee(employee, idEmployee);
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse(id, true, null));
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
