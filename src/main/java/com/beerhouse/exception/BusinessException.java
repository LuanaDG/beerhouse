package com.beerhouse.exception;

public class BusinessException extends RuntimeException{
	
	private static final long serialVersionUID = -7517461125276605789L;
	

	public BusinessException(Object obj) {
		super(obj.toString());
	}

}
