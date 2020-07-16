package com.service.order.exceptions;

@SuppressWarnings("serial")
public class RecordInsertionException extends RuntimeException{
	
	public RecordInsertionException(String exDesc) {
		super(exDesc);
	}

}
