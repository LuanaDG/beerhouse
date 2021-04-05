package com.beerhouse.service;

import java.util.List;
import java.util.Optional;
import com.beerhouse.entity.Beer;

public interface BeerService {

	Optional <Beer> findBeerById (Integer id);
	
	List<Beer> listBeerRegistered();
	
	Beer insertNewBeer(Beer beer);
	
	void deleteBeerById(Integer id);
	
	Beer updateBeer(Integer id, Beer obj);
	
	void validateIfNameAlreadyExists (String name);
	
}
