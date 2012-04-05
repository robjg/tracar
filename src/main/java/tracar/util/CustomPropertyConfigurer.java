package tracar.util;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionVisitor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.StringValueResolver;

public class CustomPropertyConfigurer implements BeanFactoryPostProcessor {
	
	private final Properties properties;
	
	public CustomPropertyConfigurer(Properties properties) {
		this.properties = properties;
	}
	
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactoryToProcess)
	throws BeansException {
		
		BeanDefinitionVisitor visitor = new BeanDefinitionVisitor(
				new BeanDirectoryResolver());
		
		String[] beanNames = 
				beanFactoryToProcess.getBeanDefinitionNames();
		for (int i = 0; i < beanNames.length; i++) {

			BeanDefinition bd = beanFactoryToProcess.getBeanDefinition(
					beanNames[i]);
			try {
				visitor.visitBeanDefinition(bd);
			}
			catch (BeanDefinitionStoreException ex) {
				throw new BeanDefinitionStoreException(
						bd.getResourceDescription(), beanNames[i], 
						ex.getMessage());
			}
		}
	}
	
	class BeanDirectoryResolver implements StringValueResolver {

		private final PropertyPlaceholderHelper helper; 
		
		public BeanDirectoryResolver() {
			helper = new PropertyPlaceholderHelper("${", "}");
		}
		
		public String resolveStringValue(String strVal) {
			return helper.replacePlaceholders(strVal, properties);
		}
	}
}
