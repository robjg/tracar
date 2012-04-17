package tracar.util;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class CustomPropertyConfigurerTest {

	@Test
	public void testCustomConfiguration() {
	
		Properties properties = new Properties();
		properties.setProperty("favourite.fruit", "an apple");
		
		ConfigurableApplicationContext context = 
			new ClassPathXmlApplicationContext(
				new String[] {
					"tracar/util/CustomPropertyConfigurerTest1.spg.xml"},
				false);

		context.addBeanFactoryPostProcessor(
				new CustomPropertyConfigurer(properties));
		context.refresh();
		
		SomeBean results = context.getBean(SomeBean.class);
		
		assertEquals("My favourite fruit is an apple", 
				results.one);
		
		assertEquals("My favourite colour is ${favourite.colour}", 
				results.two);
		
		context.close();
	}
	
	@Test
	public void testCustomConfigurationAndSprings() {
		
		Properties properties = new Properties();
		properties.setProperty("favourite.fruit", "an apple");
		
		ConfigurableApplicationContext context = 
			new ClassPathXmlApplicationContext(
				new String[] {
					"tracar/util/CustomPropertyConfigurerTest2.spg.xml"},
				false);

		context.addBeanFactoryPostProcessor(
				new CustomPropertyConfigurer(properties));
		context.refresh();
		
		SomeBean results = context.getBean(SomeBean.class);
		
		assertEquals("My favourite fruit is an apple", 
				results.one);
		
		assertEquals("My favourite colour is blue", 
				results.two);
		
		context.close();
	}
	
	@Test
	public void testCustomConfigurationSelectedProperties() {
		
		Properties properties = new Properties();
		properties.setProperty("selector.choice", "Test");
		
		ConfigurableApplicationContext context = 
			new ClassPathXmlApplicationContext(
				new String[] {
					"tracar/util/CustomPropertyConfigurerTest3.spg.xml"},
				false);

		context.addBeanFactoryPostProcessor(
				new CustomPropertyConfigurer(properties));
		context.refresh();
		
		SomeBean results = context.getBean(SomeBean.class);
		
		assertEquals("My favourite fruit is an orange", 
				results.one);
		
		assertEquals("My favourite colour is blue", 
				results.two);
		
		context.close();
	}
	
	public static class SomeBean  {
		
		private String one;
		
		private String two;
		
		public void setOne(String one) {
			this.one = one;
		}

		public void setTwo(String two) {
			this.two = two;
		}
	}	
}
