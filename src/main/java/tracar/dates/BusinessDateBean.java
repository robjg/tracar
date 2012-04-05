package tracar.dates;

import java.util.Date;

/**
 * Bean for {@link BusinessDate}
 * 
 * @author rob
 *
 */
public class BusinessDateBean implements BusinessDate {

	private long id;
	
	private Date previous;
	
	private Date current;
	
	private Date next;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getPrevious() {
		return previous;
	}

	public void setPrevious(Date previous) {
		this.previous = previous;
	}

	public Date getCurrent() {
		return current;
	}

	public void setCurrent(Date current) {
		this.current = current;
	}

	public Date getNext() {
		return next;
	}

	public void setNext(Date next) {
		this.next = next;
	}
}
