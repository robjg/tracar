package tracar.feeds.util;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class LimitingIterableTest {

	@Test
	public void testLimitingIterable() {

		List<Integer> list = Arrays.asList(1,2,3,4);
		
		LimitingIterable<Integer> test = new LimitingIterable<Integer>();
		test.setLimit(2);
		test.setLimiting(list);
		
		Iterator<Integer> iter = test.iterator();
		
		assertTrue(iter.hasNext());
		assertEquals(new Integer(1), iter.next());
		
		assertTrue(iter.hasNext());
		assertEquals(new Integer(2), iter.next());
		
		assertFalse(iter.hasNext());
		assertNull(iter.next());
	}

}
