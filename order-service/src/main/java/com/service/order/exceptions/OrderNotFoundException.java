package com.service.order.exceptions;

@SuppressWarnings("serial")
public class OrderNotFoundException extends RuntimeException {

	public OrderNotFoundException(String exDesc) {
		super(exDesc);
	}
}
