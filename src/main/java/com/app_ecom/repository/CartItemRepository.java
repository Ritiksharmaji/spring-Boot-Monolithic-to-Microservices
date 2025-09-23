package com.app_ecom.repository;

import com.app_ecom.model.CartItem;
import com.app_ecom.model.Product;
import com.app_ecom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByUserAndProduct(User user, Product product);

    void deleteByUserAndProduct(User user, Product product);

    // Correct: find by User entity
    List<CartItem> findByUser(User user);

    // Optional alternative: find by userId (use Long not String)
    List<CartItem> findByUserId(Long userId);


    void deleteByUser(User user);
}
