package com.shopping.cart.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.entity.User;
import com.shopping.cart.entity.UserDetail;
import com.shopping.cart.repository.UserDetailRepository;
import com.shopping.cart.repository.UserRepository;



@Service
public class UserDetailService {
    private final UserDetailRepository userDetailRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserDetailService(UserDetailRepository userDetailRepository, UserRepository userRepository) {
        this.userDetailRepository = userDetailRepository;
        this.userRepository = userRepository;
    }

    public UserDetail addUserDetail(Long userId, UserDetail userDetail) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        userDetail.setUser(user);
        return userDetailRepository.save(userDetail);
    }
}

