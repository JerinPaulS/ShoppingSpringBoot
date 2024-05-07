package com.shop.shopping.contollers;

import com.shop.shopping.entities.Product;
import com.shop.shopping.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.shop.shopping.exceptions.ProductMinimumPriceException;

import java.util.List;
import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private ProductService service;

    public ProductRestController(ProductService service)  {
        this.service = service;
    }

    @GetMapping
    public List<Product> getProducts() {
        return service.findAllProducts();
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return ResponseEntity.of(service.findProductById(id));
    }

    @GetMapping(params = "min")
    public List<Product> getProductsByMinPrice(@RequestParam(defaultValue = "0.0") double min) {
        if (min < 0) throw new ProductMinimumPriceException(min);
        return service.findAllProductsByMinPrice(min);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product product1 = service.saveProduct(product);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("{id}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(location).body(product1);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return service.findProductById(id)
                .map(p -> {
                    p.setName(product.getName());
                    p.setPrice(product.getPrice());
                    return ResponseEntity.ok(service.saveProduct(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return service.findProductById(id)
                .map(p -> {
                    service.deleteProduct(p);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllProducts() {
        service.deleteAllProducts();
        return ResponseEntity.noContent().build();
    }
}
