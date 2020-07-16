package com.service.item.exceptions;

@SuppressWarnings("serial")
public class RecordInsertionException extends RuntimeException{
	
	public RecordInsertionException(String exDesc) {
		super(exDesc);
	}

}
