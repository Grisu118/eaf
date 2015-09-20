package edu.spring;

/**
 * Created by benjamin on 14.09.2015.
 */
public class ExternalizedConstructorHelloWorldMessageProvider implements MessageProvider {

    private String message;

    public ExternalizedConstructorHelloWorldMessageProvider(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
