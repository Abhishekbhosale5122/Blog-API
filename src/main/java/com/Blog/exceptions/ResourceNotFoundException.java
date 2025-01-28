package com.Blog.exceptions;

import org.apache.logging.log4j.message.StringFormattedMessage;

public class ResourceNotFoundException extends RuntimeException {

	String fieldName;
	String resourceName;
	long fieldValue;

	public ResourceNotFoundException(String fieldName, String resourceName, long fieldValue) {
		super(String.format("%s not found with %s : %s ", resourceName, fieldName, fieldValue));
		this.fieldName = fieldName;
		this.resourceName = resourceName;
		this.fieldValue = fieldValue;
	}

}
