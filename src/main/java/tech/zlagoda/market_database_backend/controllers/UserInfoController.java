package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.Credentials;
import tech.zlagoda.market_database_backend.pojos.Employee;
import tech.zlagoda.market_database_backend.pojos.UserInfo;
import tech.zlagoda.market_database_backend.services.EmployeesService;
import tech.zlagoda.market_database_backend.services.UserInfoService;

@RestController
@RequestMapping("/users")
public class UserInfoController {
    @Autowired
    UserInfoController(UserInfoService userInfoService, EmployeesService employeesService) {
        this.userInfoService = userInfoService;
        this.employeesService = employeesService;
    }

    private final UserInfoService userInfoService;
    private final EmployeesService employeesService;

    @Secured("Manager")
    @PostMapping("/{idEmployee}")
    public ResponseEntity<String> addUserInfo(@RequestBody Credentials credentials,
                                               @PathVariable String idEmployee) {
        Employee employee = employeesService.getEmployee(idEmployee);
        UserInfo info = new UserInfo();
        info.setUsername(credentials.getUsername());
        info.setPassword(credentials.getPassword());
        info.setEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body(userInfoService.addUserInfo(info));
    }

    @Secured("Manager")
    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteCategory(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(userInfoService.deleteUserInfo(username));
    }
}