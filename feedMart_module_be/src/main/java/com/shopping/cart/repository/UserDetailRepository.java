package com.shopping.cart.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.cart.entity.UserDetail;


@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
  
}
