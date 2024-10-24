package org.nightfury.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.nightfury.entity.Product;
import org.nightfury.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public Product getProductById(long id) {
        return productRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("The product with ID: " + id + " not found"));
    }

    public Product getProductByTitle(String title) {
        return productRepository.findProductByTitle(title).orElseThrow(() ->
            new EntityNotFoundException("The product with title: " + title + " not found"));
    }

    public Page<Product> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    public Page<Product> getAllProductsBetweenPrice(Optional<Double> minPrice,
        Optional<Double> maxPrice, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Double min = minPrice.orElse(0.0);
        Double max = maxPrice.orElse(Double.MAX_VALUE);
        return productRepository.findByPriceBetween(min, max, pageable);
    }

    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(long id) {
        Product product = getProductById(id);
        productRepository.deleteById(product.getId());
    }
}
