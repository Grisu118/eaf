package edu.spring;

/**
 * Created by benjamin on 14.09.2015.
 */
public class StandardOutRenderer implements MessageRenderer {
    private MessageProvider messageProvider;

    @Override
    public void render() {
        System.out.println(messageProvider.getMessage());
    }

    @Override
    public void setMessageProvider(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }
}
