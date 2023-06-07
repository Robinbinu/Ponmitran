package com.shopping.cart.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.dto.CartDto;
import com.shopping.cart.dto.ProductDto;
import com.shopping.cart.dto.UserDto;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.Product;
import com.shopping.cart.entity.User;
import com.shopping.cart.repository.CartRepository;
import com.shopping.cart.repository.ProductRepository;
import com.shopping.cart.repository.UserRepository;
import com.shopping.cart.service.ProductService;
import com.shopping.cart.values.Status;

import jakarta.transaction.Transactional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/cartData")
public class CartController {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductService productService;

    @PostMapping("/users/{userId}/addProduct/{productId}")
    public List<Cart> addProductToCart(@PathVariable Long userId, @PathVariable Long productId) {
        User user = userRepository.findById(userId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if (user == null || product == null) {
            return null;
        }

        List<Cart> cartList = cartRepository.findByUserAndProduct(user, product);

        if (!cartList.isEmpty()) {
            // product already exists in user's cart, increment the quantity
            Cart cartItem = cartList.get(0);
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartRepository.save(cartItem);
        } else {
            // product does not exist in user's cart, add a new cart item
            Cart cartItem = new Cart(user, product, 1);
            cartRepository.save(cartItem);
        }
        return cartRepository.findByUserAndProduct(user, product);
       // return Status.SUCCESS;
    }

    @Transactional
    @GetMapping("/users/{userId}/cartItems")
    public List<CartDto> getCartItems(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        List<Cart> cartItems = cartRepository.findByUser(user);

        // Map Cart entities to CartDTO objects
        List<CartDto> cartDTOs = cartItems.stream()
                .map(this::mapToCartDTO)
                .collect(Collectors.toList());

        return cartDTOs;
    }

    private CartDto mapToCartDTO(Cart cart) {
        CartDto cartDTO = new CartDto();
        cartDTO.setId(cart.getId());
        cartDTO.setUser(mapToUserDto(cart.getUser()));
        cartDTO.setProduct(mapToProductDto(cart.getProduct()));
        cartDTO.setQuantity(cart.getQuantity());
        return cartDTO;
    }

    private UserDto mapToUserDto(User user) {
        // Implement the mapping logic from User to UserDTO
        // Return a UserDTO object
    	UserDto userDto = new UserDto();
    	userDto.setId(user.getId());
    	userDto.setUsername(user.getUsername());
    	userDto.setLoggedIn(user.isLoggedIn());
    	userDto.setPassword(user.getPassword());
    	return userDto;
    }

    private ProductDto mapToProductDto(Product product) {
        // Implement the mapping logic from Product to ProductDTO
        // Return a ProductDTO object
    	ProductDto productDto = new ProductDto();
    	productDto.setProduct_id(product.getProduct_id());
    	productDto.setProduct_name(product.getProduct_name());
    	productDto.setProduct_url(product.getProduct_url());
    	productDto.setProduct_price(product.getProduct_price());
    	productDto.setQuantity(product.getQuantity());
    	productDto.setCategory(product.getCategory());
    	return productDto;
    }


    @PostMapping("/users/{userId}/removeProduct/{productId}")
    public Status removeProductFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        User user = userRepository.findById(userId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if (user == null || product == null) {
            return Status.FAILURE;
        }

        List<Cart> cartList = cartRepository.findByUserAndProduct(user, product);

        if (cartList.isEmpty()) {
            // product does not exist in user's cart
            return Status.FAILURE;
        } else {
            // decrement the quantity of the product in user's cart
            Cart cartItem = cartList.get(0);
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartRepository.save(cartItem);
            } else {
                // remove the cart item if quantity becomes zero
                cartRepository.delete(cartItem);
            }
            return Status.SUCCESS;
        }
    }
    

    @DeleteMapping("/users/{userId}/cartItems")
    public Status deleteCartItems(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return Status.FAILURE;
        }
		return Status.DELETED;
}
}