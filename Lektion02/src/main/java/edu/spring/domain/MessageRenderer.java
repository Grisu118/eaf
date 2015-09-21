package main.java.edu.spring.domain;


public interface MessageRenderer {
	void setMessageProvider(MessageProvider mp);
	MessageProvider getMessageProvider();
	void render();
}
