package com.service.item.exceptions;

@SuppressWarnings("serial")
public class RecordNotFoundException extends RuntimeException {

	public RecordNotFoundException(String exDesc) {
		super(exDesc);
	}

}
