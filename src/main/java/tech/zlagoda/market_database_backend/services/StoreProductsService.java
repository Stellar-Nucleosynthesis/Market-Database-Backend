package tech.zlagoda.market_database_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.zlagoda.market_database_backend.pojos.StoreProduct;
import tech.zlagoda.market_database_backend.pojos.StoreProductInfo;
import tech.zlagoda.market_database_backend.repositories.StoreProductsRepository;

import java.util.List;

import static tech.zlagoda.market_database_backend.validators.StoreProductValidator.validate;

@Service
public class StoreProductsService {
    @Autowired
    StoreProductsService(StoreProductsRepository repository) {
        this.repository = repository;
    }

    private final StoreProductsRepository repository;

    public String addStoreProduct(StoreProduct storeProduct) {
        validate(storeProduct);
        repository.addStoreProduct(storeProduct);
        return storeProduct.getUpc();
    }

    public String deleteStoreProduct(String upc) {
        repository.deleteStoreProduct(upc);
        return upc;
    }

    public String updateStoreProduct(StoreProduct storeProduct, String upc) {
        validate(storeProduct);
        storeProduct.setUpc(upc);
        repository.updateStoreProduct(storeProduct);
        return storeProduct.getUpc();
    }

    public List<StoreProduct> getStoreProducts(Boolean promotional, String sortBy) {
        return repository.getStoreProducts(promotional, sortBy);
    }

    public StoreProductInfo getStoreProductInfo(String upc) {
        return repository.getStoreProductInfo(upc);
    }
}
