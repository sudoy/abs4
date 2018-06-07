package abs4.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import abs4.beans.Category;

public class Utils {
	public static LocalDate date2LocalDate(final Date date) {
		if(date == null) {
			return null;
		}
		return date.toLocalDate();
	}


	public static List<Category> allCategories() {

		List<Category> categories = new ArrayList<>();
		categories.add(new Category(
				0, "選択してください。"));

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBUtils.getConnection();

			String sql = "SELECT id, type FROM categories ORDER BY id";

			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				categories.add(new Category(
						rs.getInt("id"),
						rs.getString("type")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps);
		}

		return categories;
	}
}
