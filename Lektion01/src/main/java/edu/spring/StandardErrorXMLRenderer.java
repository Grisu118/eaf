package edu.spring;

/**
 * Created by benjamin on 14.09.2015.
 */
public class StandardErrorXMLRenderer implements MessageRenderer {
    private MessageProvider messageProvider;

    @Override
    public void render() {
        System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<error>" + messageProvider.getMessage() + "</error>");
    }

    @Override
    public void setMessageProvider(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }
}
