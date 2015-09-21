package edu.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.PropertyOverrideConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by benjamin on 14.09.2015.
 */
public class DecoupledHelloWorldWithSpring {

    public static void main(String[] args) {

        ApplicationContext c = getContext();
        MessageRenderer mr = (MessageRenderer) c.getBean("renderer");
        mr.render();
    }
    private static ConfigurableApplicationContext  getContext() {
        return new ClassPathXmlApplicationContext("/spring/helloConfig.xml");
    }
}

