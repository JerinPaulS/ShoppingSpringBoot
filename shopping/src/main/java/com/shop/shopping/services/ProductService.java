package com.shop.shopping.services;

import com.shop.shopping.dao.ProductRepository;
import com.shop.shopping.entities.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void intializeDatabase() {
        if (productRepository.count() == 0) {
            productRepository.saveAll(
                    List.of(
                            new Product("TV tray", BigDecimal.valueOf(4.95)),
                            new Product("Toaster", BigDecimal.valueOf(19.95)),
                            new Product("Sofa", BigDecimal.valueOf(249.95))
                    )
            );
        }
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Product p) {
        productRepository.delete(p);
    }

    public void deleteAllProducts() {
        productRepository.deleteAllInBatch();
    }

    public List<Product> findAllProductsByMinPrice(double min) {
        return productRepository.findAllByPriceGreaterThanEqual(BigDecimal.valueOf(min));
    }
}
