//package com.shop.shopping.config;
//
//import com.shop.shopping.dao.ProductRepository;
//import com.shop.shopping.entities.Product;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@Component
//public class AppInit implements CommandLineRunner {
//
//    private final ProductRepository productRepository;
//
//    public AppInit(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    @Override
//    public void run(String... args) {
//        if (productRepository.count() == 0) {
//            productRepository.saveAll(
//                    List.of(
//                            new Product("TV tray", BigDecimal.valueOf(4.95)),
//                            new Product("Toaster", BigDecimal.valueOf(19.95)),
//                            new Product("Sofa", BigDecimal.valueOf(249.95))
//                    )
//            );
//        }
//    }
//}
