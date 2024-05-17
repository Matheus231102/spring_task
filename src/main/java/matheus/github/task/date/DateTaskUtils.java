package matheus.github.task.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTaskUtils {

	public static LocalDateTime stringToLocalDateTime(String date) {
		return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
	}

}
