package edu.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by benjamin on 14.09.2015.
 */
public class DecoupledHelloWorldWithSpring {

    public static void main(String[] args) {
        BeanFactory factory = getBeanFactory();
        MessageRenderer mr = (MessageRenderer) factory.getBean("renderer");
        mr.render();
    }

    private static BeanFactory getBeanFactory() {
        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("/spring/helloConfig.xml"));
        return factory;
    }
}

