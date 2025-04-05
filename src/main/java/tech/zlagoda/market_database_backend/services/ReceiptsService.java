package tech.zlagoda.market_database_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tech.zlagoda.market_database_backend.pojos.Receipt;
import tech.zlagoda.market_database_backend.pojos.Sale;
import tech.zlagoda.market_database_backend.repositories.ReceiptsRepository;
import tech.zlagoda.market_database_backend.repositories.SalesRepository;
import tech.zlagoda.market_database_backend.repositories.UserInfoRepository;
import tech.zlagoda.market_database_backend.validators.SaleValidator;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static tech.zlagoda.market_database_backend.validators.ReceiptValidator.validate;

@Service
public class ReceiptsService {
    @Autowired
    public ReceiptsService(ReceiptsRepository receiptsRepository,
                           SalesRepository salesRepository,
                           UserInfoRepository userInfoRepository) {
        this.receiptsRepository = receiptsRepository;
        this.salesRepository = salesRepository;
        this.userInfoRepository = userInfoRepository;
    }

    private final ReceiptsRepository receiptsRepository;
    private final SalesRepository salesRepository;
    private final UserInfoRepository userInfoRepository;

    public String addReceipt(Receipt receipt) {
        validate(receipt);
        receiptsRepository.addReceipt(receipt);
        for(Sale sale : receipt.getSales()) {
            SaleValidator.validate(sale);
            salesRepository.addSale(sale);
        }
        return receipt.getReceiptNumber();
    }

    public String deleteReceipt(String receiptNumber) {
        receiptsRepository.deleteReceipt(receiptNumber);
        salesRepository.deleteSales(receiptNumber);
        return receiptNumber;
    }

    public List<Receipt> getReceipts(String idEmployee, Date from, Date to) {
        List<Receipt> receipts = receiptsRepository.getReceipts(idEmployee, from, to);
        for (Receipt receipt : receipts) {
            receipt.setSales(salesRepository.getSales(receipt.getReceiptNumber()));
        }
        return receipts;
    }

    public Receipt getReceipt(String receiptNumber) {
        Receipt receipt = receiptsRepository.getReceipt(receiptNumber);
        receipt.setSales(salesRepository.getSales(receipt.getReceiptNumber()));
        return receipt;
    }

    public BigDecimal getTotal(String idEmployee, Date from, Date to){
        BigDecimal res = BigDecimal.ZERO;
        for(Receipt receipt : receiptsRepository.getReceipts(idEmployee, from, to)) {
            for(Sale sale : salesRepository.getSales(receipt.getReceiptNumber())) {
                res = res.add(sale.getSellingPrice().multiply(new BigDecimal(sale.getProductNumber())));
            }
        }
        return res;
    }

    public Integer getQuantity(String upc, Date from, Date to) {
        int res = 0;
        for(Receipt receipt : receiptsRepository.getReceipts(null, from, to)) {
            for(Sale sale : salesRepository.getSales(receipt.getReceiptNumber())) {
                if(sale.getUPC().equals(upc)) {
                    res += sale.getProductNumber();
                }
            }
        }
        return res;
    }

    public List<Receipt> getMyReceipts(Date from, Date to){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String idEmployee = userInfoRepository.getUserInfo(username).getEmployee().getIdEmployee();
        return getReceipts(idEmployee, from, to);
    }
}
