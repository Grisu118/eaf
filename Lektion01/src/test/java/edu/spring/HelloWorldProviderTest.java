package edu.spring;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by benjamin on 14.09.2015.
 */
public class HelloWorldProviderTest {
    private HelloWorldProvider p = new HelloWorldProvider();

    @Test
    public void testGetMessage() throws Exception {
        assertEquals("Hello World!", p.getMessage());
    }
}