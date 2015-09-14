package edu.spring;

/**
 * Created by benjamin on 14.09.2015.
 */
public class HelloWorldProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello World!";
    }
}
