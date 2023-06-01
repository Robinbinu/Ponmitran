package com.shopping.cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.Product;
import com.shopping.cart.entity.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserId(Long userId);

    Optional<Cart> findByUserIdAndProductId(Long userId, Long productId);

	List<Cart> findByUserAndProduct(User user, Product product);

	List<Cart> findByUser(User user);
}

