package com.diagnoPlant.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.diagnoPlant.models.Image;
import com.diagnoPlant.models.User;


public interface ImageRepository extends JpaRepository<Image,Long>{
	
	public Page<Image> findAllByUser(User user, Pageable pageable);

}