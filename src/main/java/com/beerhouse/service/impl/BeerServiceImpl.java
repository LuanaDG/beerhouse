package com.beerhouse.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.beerhouse.entity.Beer;
import com.beerhouse.exception.BusinessException;
import com.beerhouse.exception.ResourceNotFoundException;
import com.beerhouse.repository.BeerRepository;
import com.beerhouse.service.BeerService;


@Service
public class BeerServiceImpl implements BeerService {

	@Autowired
	private BeerRepository repository;

	@Override
	public Optional<Beer> findBeerById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public List<Beer> listBeerRegistered() {
		return repository.findAll();
	}

	@Override
	public Beer insertNewBeer(Beer beer) {
		executeValidations(beer);
		validateIfNameAlreadyExists(beer.getName());
		return repository.save(beer);
	}

	@Override
	public void deleteBeerById(Integer id) {

		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} 
	}

	@Override
	public Beer updateBeer(Integer id, Beer beer) {

		try {
			if (!repository.existsById(id)) {
				throw new ResourceNotFoundException("A cerveja com o ID:" + id + " não existe.");
			}
			executeValidations(beer);
			validateIfNameAlreadyExists(beer.getName());
			Beer entity = repository.getOne(id);
			updateData(entity, beer);
			return repository.save(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("A cerveja com o ID:"+id+" não existe na base de dados.");
		}
	}
	
	private void updateData(Beer entity, Beer obj) {
		entity.setName(obj.getName());
		entity.setIngredients(obj.getIngredients());
		entity.setAlcoholContent(obj.getAlcoholContent());
		entity.setPrice(obj.getPrice());
		entity.setCategory(obj.getCategory());
	}

	public Beer executeValidations(Beer beer) {

		if (beer.getName() == null || beer.getName().trim().equals("")) {
			throw new BusinessException("O campo Nome deve ser preenchido!");
		}
		if (beer.getAlcoholContent() == null || beer.getAlcoholContent().trim().equals("")) {
			throw new BusinessException("O campo Teor Alcólico deve ser preenchido!");
		}
		if (beer.getIngredients() == null || beer.getIngredients().trim().equals("")) {
			throw new BusinessException("O campo Ingredientes deve ser preenchido!");
		}
		if (beer.getPrice() == null) {
			throw new BusinessException("O campo Preço deve ser preenchido!");
		}
		if (beer.getPrice().compareTo(BigDecimal.ZERO) <= 0 ) {
			throw new BusinessException("O campo Preço deve ser maior que zero!");
		}
		if (beer.getCategory() == null || beer.getCategory().trim().equals("")) {
			throw new BusinessException("O campo Categoria deve ser preenchido!");
		}
		
		return beer;
	}
	
	@Override 
	public void validateIfNameAlreadyExists(String name){
		boolean alreadyExists = repository.existsByName(name);
		if(alreadyExists) {
			throw new BusinessException("Já existe uma cerveja cadastrada com este nome!");
		}
	}
}
