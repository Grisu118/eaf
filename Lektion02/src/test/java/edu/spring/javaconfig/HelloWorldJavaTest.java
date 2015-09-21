package test.java.edu.spring.javaconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.spring.domain.MessageProvider;
import edu.spring.domain.MessageRenderer;

@RunWith(SpringJUnit4ClassRunner.class)
public class HelloWorldJavaTest {
	@Autowired
	private MessageProvider messageProvider;
	
	@Autowired
	private MessageRenderer messageRenderer;
	
	@Test
	public void testGetMessage() {
		assertEquals("Herzlich willkommen!", messageProvider.getMessage());
	}

	@Test
	public void testGetMessageProvider() {
		assertNotNull(messageRenderer.getMessageProvider());
	}
}
