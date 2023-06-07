package com.shopping.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.entity.User;
import com.shopping.cart.repository.UserRepository;
import com.shopping.cart.values.Status;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/userData")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/users/register")
    public Status registerUser(@Validated @RequestBody User newUser) {
        List<User> users = userRepository.findAll();

        System.out.println("New user: " + newUser.toString());

        for (User user : users) {
            System.out.println("Registered user: " + newUser.toString());

            if (user.equals(newUser)) {
                System.out.println("User Already exists!");
                return Status.USER_ALREADY_EXISTS;
            }
        }

        userRepository.save(newUser);
        return Status.SUCCESS;
    }

    @PostMapping("/users/login")
    public Status loginUser(@Validated @RequestBody User user) {
        List<User> users = userRepository.findAll();

        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(true);
                userRepository.save(user);
                return Status.SUCCESS;
            }
        }

        return Status.FAILURE;
    }

    @PostMapping("/users/logout")
    public Status logUserOut(@Validated @RequestBody User user) {
        List<User> users = userRepository.findAll();

        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(false);
                userRepository.save(user);
                return Status.SUCCESS;
            }
        }

        return Status.FAILURE;
    }

    @DeleteMapping("/users/all")
    public Status deleteUsers() {
        userRepository.deleteAll();
        return Status.SUCCESS;
    }
    
//    @PostMapping("/users/add-to-cart/{productId}")
//    public Status addProductToCart(@PathVariable Long productId, @Validated @RequestBody User user) {
//        User loggedInUser = userRepository.findByUsername(user.getUsername());
//         
//        CartService cartService = null;
//        if (loggedInUser != null && loggedInUser.isLoggedIn()) {
//            cartService.addProductToCart(productId, loggedInUser);
//            return Status.SUCCESS;
//        }
//
//        return Status.FAILURE;
//    }
}