package tech.zlagoda.market_database_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.zlagoda.market_database_backend.pojos.Product;
import tech.zlagoda.market_database_backend.repositories.ProductsRepository;

import java.util.List;

import static tech.zlagoda.market_database_backend.validators.ProductValidator.validate;

@Service
public class ProductsService {
    @Autowired
    ProductsService(ProductsRepository repository) {
        this.repository = repository;
    }

    private final ProductsRepository repository;

    public Integer addProduct(Product product) {
        validate(product);
        repository.addProduct(product);
        return product.getIdProduct();
    }

    public Integer deleteProduct(int idProduct) {
        repository.deleteProduct(idProduct);
        return idProduct;
    }

    public Integer updateProduct(Product product, int idProduct) {
        validate(product);
        product.setIdProduct(idProduct);
        repository.updateProduct(product);
        return product.getIdProduct();
    }

    public List<Product> getProducts(String productName, Integer categoryNumber, String sortBy) {
        return repository.getProducts(productName, categoryNumber, sortBy);
    }
}
