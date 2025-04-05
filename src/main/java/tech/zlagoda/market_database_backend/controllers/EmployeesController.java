package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.Employee;
import tech.zlagoda.market_database_backend.repositories.EmployeesRepository;
import tech.zlagoda.market_database_backend.repositories.UserInfoRepository;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    EmployeesController(EmployeesRepository repository, UserInfoRepository userInfoRepository) {
        this.repository = repository;
        this.userInfoRepository = userInfoRepository;
    }

    private final EmployeesRepository repository;
    private final UserInfoRepository userInfoRepository;

    @Secured({"Manager"})
    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee){
        repository.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body(employee.getIdEmployee());
    }

    @Secured({"Manager"})
    @DeleteMapping("/{idEmployee}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String idEmployee){
        repository.deleteEmployee(idEmployee);
        return ResponseEntity.status(HttpStatus.OK).body(idEmployee);
    }

    @Secured({"Manager"})
    @PutMapping("/{idEmployee}")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee, @PathVariable String idEmployee){
        employee.setIdEmployee(idEmployee);
        repository.updateEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body(employee.getIdEmployee());
    }

    @Secured({"Manager"})
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> getEmployees(
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String role){
        return ResponseEntity.status(HttpStatus.OK).body(repository.getEmployees(surname, role));
    }

    @Secured({"Manager", "Cashier"})
    @GetMapping("/me")
    public ResponseEntity<Employee> getMe(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.status(HttpStatus.OK).body(userInfoRepository.getUserInfo(username).getEmployee());
    }
}
