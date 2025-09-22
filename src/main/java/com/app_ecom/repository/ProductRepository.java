package com.app_ecom.repository;

import com.app_ecom.dto.ProductResponse;
import com.app_ecom.model.Product;
import com.app_ecom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // by giving right name of method JPA will understand the make Query on that
    List<Product> findByActiveTrue();

    @Query("SELECT p FROM Product p WHERE p.active = true AND CAST(p.stockQuantity AS int) > 0 AND LOWER(p.name) LIKE CONCAT('%', :keyword, '%')")
    List<Product> searchProducts(@Param("keyword") String keyword);



    // List<Product> findByActiveTrueAndStockQuantityGreaterThanAndNameContainingIgnoreCase(int stockQuantity, String name);
}
