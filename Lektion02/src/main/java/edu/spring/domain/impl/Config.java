package edu.spring.domain.impl;

import edu.spring.domain.MessageProvider;
import edu.spring.domain.MessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by benjamin on 21.09.2015.
 */
@Configuration
@PropertySource("classpath:app.properties")
public class Config {

    @Bean
    public MessageProvider externalizedHelloWorldMessageProvider(){
        return new ExternalizedHelloWorldMessageProvider();
    }

    @Bean
    public MessageRenderer standartOutRenderer() {
        return new StandardOutRenderer();
    }

}
