package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.CategorySoldInfo;
import tech.zlagoda.market_database_backend.pojos.Employee;
import tech.zlagoda.market_database_backend.pojos.EmployeeAverageSaleInfo;
import tech.zlagoda.market_database_backend.pojos.StoreProductInfo;
import tech.zlagoda.market_database_backend.services.SpecificInfoService;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/specific_info")
public class SpecificInfoController {
    @Autowired
    SpecificInfoController(SpecificInfoService service) {
        this.service = service;
    }

    private final SpecificInfoService service;

    @Secured("Manager")
    @GetMapping("/sold_by_category")
    public ResponseEntity<List<CategorySoldInfo>> getNumSoldByCategory(
            @RequestParam String idEmployee,
            @RequestParam Date from,
            @RequestParam Date to) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getNumSoldByCategory(idEmployee, from, to));
    }

    @Secured("Manager")
    @GetMapping("/no_discount_sales_employees")
    public ResponseEntity<List<Employee>> getNoDiscountSalesEmployees() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getNoDiscountSalesEmployees());
    }

    @Secured("Manager")
    @GetMapping("/average_cashier_sale")
    public ResponseEntity<List<EmployeeAverageSaleInfo>> getAverageCashierSale() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAverageCashierSale());
    }

    @Secured("Manager")
    @GetMapping("/unsold_without_discount")
    public ResponseEntity<List<StoreProductInfo>> getUnsoldWithNoDiscount(Date from){
        return ResponseEntity.status(HttpStatus.OK).body(service.getUnsoldWithNoDiscount(from));
    }
}
