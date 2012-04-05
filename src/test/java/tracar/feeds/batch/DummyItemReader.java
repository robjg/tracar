package tracar.feeds.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class DummyItemReader implements ItemReader<String> {

	String[] strings = {"apple", "orange"};
	
	int index;
	
	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		if (index < strings.length) {
			return strings[index++];
		}
		else {
			return null;
		}
	}
}
