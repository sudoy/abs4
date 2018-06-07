package abs4.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		return String.format("%,d", detail.getCost());
	}

	public static String checkDivision(String param, String value) {
		if (param.equals(value)) {
			return "checked";
		} else {
			return "";
		}
	}

	public static String makeOptionCategories(String value) {
		StringBuilder sb = new StringBuilder();
		sb.append("<option value='0'>選択してください</option>");

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBUtils.getConnection();

			String sql = "SELECT id, type FROM categories ORDER BY id";

			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				String selected = "";
				if(value.equals(rs.getString("id"))) {
					selected = "selected";
				}
				sb.append(String.format(
						"<option value='%d' %s>%s</option>", rs.getInt("id"), selected, rs.getString("type")
					));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps);
		}


		return sb.toString();
	}
}
