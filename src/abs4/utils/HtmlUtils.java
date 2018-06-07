package abs4.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import abs4.beans.Detail;

public class HtmlUtils {
	public static String formatDay(Detail abs4) {
		LocalDate day = abs4.getDay();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return day.format(dtf);

	}

	public static String formatCost(Detail abs4) {
		String cost2 = new String();
		return cost2 = String.format("%,d", abs4.getCost() );
	}



}
