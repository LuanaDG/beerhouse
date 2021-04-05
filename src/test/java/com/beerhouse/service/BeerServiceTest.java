package com.beerhouse.service;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.beerhouse.entity.Beer;
import com.beerhouse.exception.BusinessException;
import com.beerhouse.repository.BeerRepository;
import com.beerhouse.service.impl.BeerServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BeerServiceTest {

	@Autowired
	@SpyBean
	BeerServiceImpl service;

	@MockBean
	BeerRepository repository;

	@Test
	public void testInsertNewBeer() {

		String name = "cerveja";
		String ingredients = "malte";
		String alcoholContent = "3%";
		BigDecimal price = new BigDecimal(12.5);
		String category = "artesanal";

		Beer beer = new Beer(123, name, ingredients, alcoholContent, price, category);

		Mockito.when(repository.save(Mockito.any(Beer.class))).thenReturn(beer);

		Beer beerInserted = service.insertNewBeer(beer);

		Assertions.assertEquals(123, beerInserted.getId());
		Mockito.verify(service, Mockito.atLeastOnce()).validateIfNameAlreadyExists(Mockito.any(String.class));
		Mockito.verify(repository, Mockito.atLeastOnce()).save(beer);
	}

	@Test
	public void exceptionWhenInsertNewBeerWithoutName() {

		Assertions.assertThrows(BusinessException.class, () -> {

			Beer beer = new Beer(null, "", null, null, null, null);

			Mockito.when(repository.save(Mockito.any(Beer.class))).thenReturn(beer);

			service.insertNewBeer(beer);

			Mockito.verify(repository, Mockito.never()).save(beer);
		});
	}

	@Test
	public void notRegisterBeerWithNameAlreadyRegistered() {

		String name = "American Larger";
		String ingredients = "trigo";
		String alcoholContent = "4%";
		BigDecimal price = new BigDecimal(10);
		String category = "artesanal";
		Beer beer = new Beer(null, name, ingredients, alcoholContent, price, category);

		Mockito.when(repository.save(Mockito.any(Beer.class))).thenReturn(beer);
		Mockito.when(repository.existsByName(Mockito.any(String.class))).thenReturn(true);
		
		Assertions.assertThrows(BusinessException.class, () -> {
			service.insertNewBeer(beer);
		});
		
		Mockito.verify(repository, Mockito.never()).save(beer);
	}

	@Test
	public void exceptionInvalidPrice() {

		BigDecimal price = new BigDecimal(-10);

		Beer beer = new Beer(null, "Ipa", "malte", "3%", price, "artesanal");

		Mockito.when(repository.save(Mockito.any(Beer.class))).thenReturn(beer);
		
		BusinessException e = Assertions.assertThrows(BusinessException.class, () -> {
			service.insertNewBeer(beer);
		});
		
		Assertions.assertEquals("O campo Preço deve ser maior que zero!", e.getMessage());
		
		
		Mockito.verify(repository, Mockito.never()).save(beer);
	}

}
