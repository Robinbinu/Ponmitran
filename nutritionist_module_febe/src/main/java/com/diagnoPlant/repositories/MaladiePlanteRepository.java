package com.diagnoPlant.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.diagnoPlant.models.MaladiePlante;


public interface MaladiePlanteRepository extends JpaRepository<MaladiePlante, Long> {

	public Page<MaladiePlante> findByNomMaladieContains(String Chercher, Pageable pageable);
}