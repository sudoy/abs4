package abs4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abs4.beans.Detail;
import abs4.utils.DBUtils;
import abs4.utils.Utils;

@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			con = DBUtils.getConnection();

			sql = "SELECT d.id, d.day, c.type, d.content, d.cost "
					+ "FROM  details d "
					+ "JOIN categories c ON d.category_id = c.id "
					+ "ORDER BY d.id";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			ArrayList<Detail> list = new ArrayList<>();

			while(rs.next()) {
				Detail abs4 = new Detail(
						rs.getInt("id"),
						Utils.date2LocalDate(rs.getDate("day")),
						rs.getString("type"),
						rs.getString("content"),
						rs.getInt("cost"));

				list.add(abs4);
			}

			req.setAttribute("list", list);
			getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}finally {
			DBUtils.close(con, ps, rs);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);

	}
}
