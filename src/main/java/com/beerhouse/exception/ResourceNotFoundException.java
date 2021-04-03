package com.beerhouse.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 8670614630805868562L;

	public ResourceNotFoundException(Object id) {
		super(" Recurso não encontrado. " + id);
	}

}
