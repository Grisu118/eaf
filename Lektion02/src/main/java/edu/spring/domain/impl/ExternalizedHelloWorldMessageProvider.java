package main.java.edu.spring.domain.impl;

import edu.spring.domain.MessageProvider;

public class ExternalizedHelloWorldMessageProvider implements MessageProvider {
	private String message;

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
