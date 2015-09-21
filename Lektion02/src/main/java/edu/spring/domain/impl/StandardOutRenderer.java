package main.java.edu.spring.domain.impl;

import edu.spring.domain.MessageProvider;
import edu.spring.domain.MessageRenderer;

public class StandardOutRenderer implements MessageRenderer {
	private MessageProvider messageProvider;

	@Override
	public void setMessageProvider(MessageProvider mp) {
		this.messageProvider = mp;
	}

	@Override
	public MessageProvider getMessageProvider() {
		return messageProvider;
	}

	@Override
	public void render() {
		System.out.println(messageProvider.getMessage());;
	}

}
