package tracar.dates;

import java.util.Date;

/**
 * Provide current, previous and next business dates.
 * 
 * @author rob
 */
public interface BusinessDate {

	public Date getPrevious();
	
	public Date getCurrent();

	public Date getNext();

}
