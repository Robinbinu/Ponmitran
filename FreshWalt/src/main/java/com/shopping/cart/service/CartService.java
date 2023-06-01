package com.shopping.cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.Product;
import com.shopping.cart.entity.User;
import com.shopping.cart.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    public List<Cart> getAllCartsByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public Optional<Cart> getCartByUserIdAndProductId(Long userId, Long productId) {
        return cartRepository.findByUserIdAndProductId(userId, productId);
    }

    public void addToCart(Long userId, Long productId, int quantity) {
        Optional<Cart> cartOptional = getCartByUserIdAndProductId(userId, productId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.setQuantity(cart.getQuantity() + quantity);
            cartRepository.save(cart);
        } else {
            Product product = productService.getProductById(productId);
            User user = new User();
            user.setId(userId);;
            Cart newCart = new Cart(product, quantity, user);
            cartRepository.save(newCart);
        }
    }

    public void removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
