package com.beerhouse.api.resource;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.beerhouse.api.dto.BeerDTO;
import com.beerhouse.entity.Beer;
import com.beerhouse.exception.BusinessException;
import com.beerhouse.exception.ResourceNotFoundException;
import com.beerhouse.response.Response;
import com.beerhouse.service.BeerService;

@RestController
@RequestMapping("/beers")
public class BeerResource {

	@Autowired
	private BeerService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Beer>> findById(@PathVariable Integer id) {
		Optional<Beer> beer = service.findBeerById(id);
		return ResponseEntity.ok().body(beer);
	}

	@GetMapping
	public ResponseEntity<List<Beer>> findAll() {
		List<Beer> list = service.listBeerRegistered();
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Response<BeerDTO>> insert(@RequestBody BeerDTO dto) {

		Response<BeerDTO> response = new Response<BeerDTO>();

		try {
			Beer beerSave = service.insertNewBeer(this.convertDtoToEntity(dto));
			response.setData(this.convertEntityToDto(beerSave));
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
			
		} catch (BusinessException e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<BeerDTO>> update(@PathVariable Integer id, @RequestBody BeerDTO dto) {

		Response<BeerDTO> response = new Response<BeerDTO>();

		try {
			Beer beerUpdate = service.updateBeer(id, this.convertDtoToEntity(dto));
			response.setData(this.convertEntityToDto(beerUpdate));

			return ResponseEntity.ok().body(response);

		} catch (BusinessException e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} catch (ResourceNotFoundException e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		service.deleteBeerById(id);
		return ResponseEntity.noContent().build();
	}

	private Beer convertDtoToEntity(BeerDTO dto) {
		Beer beer = new Beer();
		beer.setName(dto.getName());
		beer.setIngredients(dto.getIngredients());
		beer.setAlcoholContent(dto.getAlcoholContent());
		beer.setPrice(dto.getPrice());
		beer.setCategory(dto.getCategory());
		return beer;
	}

	private BeerDTO convertEntityToDto(Beer beer) {
		BeerDTO dto = new BeerDTO();
		dto.setName(beer.getName());
		dto.setIngredients(beer.getIngredients());
		dto.setAlcoholContent(beer.getAlcoholContent());
		dto.setPrice(beer.getPrice());
		dto.setCategory(beer.getCategory());
		return dto;
	}

}
