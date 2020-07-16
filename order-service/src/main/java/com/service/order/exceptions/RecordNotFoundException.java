package com.service.order.exceptions;

@SuppressWarnings("serial")
public class RecordNotFoundException extends RuntimeException {

	public RecordNotFoundException(String exDesc) {
		super(exDesc);
	}

}
