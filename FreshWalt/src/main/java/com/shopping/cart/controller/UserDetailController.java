package com.shopping.cart.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.shopping.cart.entity.UserDetail;
import com.shopping.cart.service.UserDetailService;



@RestController
@RequestMapping("/api/v1/userDetails")
public class UserDetailController {
    private final UserDetailService userDetailService;

    @Autowired
    public UserDetailController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @PostMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDetail addUserDetail(@PathVariable Long userId, @RequestBody UserDetail userDetail) {
        return userDetailService.addUserDetail(userId, userDetail);
    }
}
