package tracar.feeds.util;

import java.util.Iterator;

public class LimitingIterable<T> implements Iterable<T> {

	private int limit;
	
	private Iterable<T> limiting;
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			Iterator<T> delegate = limiting.iterator();
			int count;
			
			@Override
			public boolean hasNext() {
				if (++count > limit) {
					return false;
				}
				else {
					return delegate.hasNext();
				}
			}
			
			@Override
			public T next() {
				if (count > limit) {
					return null;
				}
				else {
					return delegate.next();
				}
			}
			
			@Override
			public void remove() {
				delegate.remove();
			}
		};
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Iterable<T> getLimiting() {
		return limiting;
	}

	public void setLimiting(Iterable<T> limiting) {
		this.limiting = limiting;
	}
}
