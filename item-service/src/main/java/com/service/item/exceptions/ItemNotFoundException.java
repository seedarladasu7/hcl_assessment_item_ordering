package com.service.item.exceptions;

@SuppressWarnings("serial")
public class ItemNotFoundException extends RuntimeException {

	public ItemNotFoundException(String exDesc) {
		super(exDesc);
	}
}
