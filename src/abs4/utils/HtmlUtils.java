package abs4.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import abs4.beans.Abs4;

public class HtmlUtils {
	public static String formatDay(Abs4 abs4) {
		LocalDate day = abs4.getDay();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return day.format(dtf);

	}

	public static String formatCost(Abs4 abs4) {
		String cost2 = new String();
		return cost2 = String.format("%,d", abs4.getCost() );
	}

	public static String Color(Abs4 abs4) {
		if(abs4.getCost() > 0) {
			return "#bee5eb";
		}
		return null;

	}
}
