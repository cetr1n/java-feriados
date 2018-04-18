import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Classe de suporte para iniciar o Calendar
 * 
 * @author Daniel Perin Tavares aka cetr1n
 */
public class GregorianCalendarWrapper {

	
	public static Calendar getInstance() {
		Calendar calendar = GregorianCalendar.getInstance();
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar;
	}
	

}
