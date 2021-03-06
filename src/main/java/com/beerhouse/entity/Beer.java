package com.beerhouse.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "beer")
public class Beer implements Serializable {
	
	private static final long serialVersionUID = 3761639375957367895L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "ingredients")
	private String ingredients;
	
	@Column(name = "alcohol_content")
	private String alcoholContent;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "category")
	private String category;

	
	public Beer() {}


	public Beer(Integer id, String name, String ingredients, String alcoholContent, 
			BigDecimal price, String category) {
		
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.alcoholContent = alcoholContent;
		this.price = price;
		this.category = category;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getIngredients() {
		return ingredients;
	}


	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}


	public String getAlcoholContent() {
		return alcoholContent;
	}


	public void setAlcoholContent(String alcoholContent) {
		this.alcoholContent = alcoholContent;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}

}


