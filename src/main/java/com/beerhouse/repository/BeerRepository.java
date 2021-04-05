package com.beerhouse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beerhouse.entity.Beer;

public interface BeerRepository extends JpaRepository <Beer, Integer> {
	
	boolean existsByName(String name);
	
	Optional<Beer> findByNameEquals(String name);

}
