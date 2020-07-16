package com.service.order.exceptions;

@SuppressWarnings("serial")
public class ItemNotFoundException extends RuntimeException {

	public ItemNotFoundException(String exDesc) {
		super(exDesc);
	}
}
