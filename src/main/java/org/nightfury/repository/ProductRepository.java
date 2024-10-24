package org.nightfury.repository;

import java.util.Optional;
import org.nightfury.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByTitle(String title);

    Page<Product> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);
}
