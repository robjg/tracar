package tracar.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

public class CustomContextBuilder {

	private Map<String, Object> beans = new LinkedHashMap<String, Object>();

	public CustomContextBuilder register(String id, Object bean) {
		beans.put(id, bean);
		return this;
	}
	
	public ApplicationContext buildContext() {

		GenericApplicationContext appContext = new StaticApplicationContext();

		for (Map.Entry<String, Object> entry : beans.entrySet()) {

			appContext.getBeanFactory().registerSingleton(
					entry.getKey(), entry.getValue());
		}

		appContext.refresh();

		return appContext;
	}
}
