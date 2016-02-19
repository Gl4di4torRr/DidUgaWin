package com.uga.wins.Exceptions;

public class ESPNParserException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ESPNParserException(String message){
		super(message);
	}
	
	public ESPNParserException(String message, Throwable thros){
		super(message, thros);
	}
}
