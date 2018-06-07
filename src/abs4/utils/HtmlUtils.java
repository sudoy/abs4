package abs4.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import abs4.beans.Detail;

public class HtmlUtils {
	public static String formatDay(Detail detail) {
		LocalDate day = detail.getDay();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return day.format(dtf);

	}

	public static String formatCost(Detail detail) {
		return String.format("%,d", detail.getCost() );
	}
}
