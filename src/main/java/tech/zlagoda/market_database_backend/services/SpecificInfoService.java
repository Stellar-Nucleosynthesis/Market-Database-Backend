package tech.zlagoda.market_database_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.zlagoda.market_database_backend.pojos.*;
import tech.zlagoda.market_database_backend.repositories.SpecificInfoRepository;

import java.sql.Date;
import java.util.List;

@Service
public class SpecificInfoService {
    @Autowired
    SpecificInfoService(SpecificInfoRepository repository) {
        this.repository = repository;
    }

    private final SpecificInfoRepository repository;

    public List<CategorySoldInfo> getNumSoldByCategory(String idEmployee, Date from, Date to) {
        return repository.getNumSoldByCategory(idEmployee, from, to);
    }

    public List<Employee> getNoDiscountSalesEmployees() {
        return repository.getNoDiscountSalesEmployees();
    }

    public List<EmployeeAverageSaleInfo> getAverageCashierSale() {
        return repository.getAverageCashierSale();
    }

    public List<StoreProductInfo> getUnsoldWithNoDiscount(Date from){
        return repository.getUnsoldWithNoDiscount(from);
    }
}
