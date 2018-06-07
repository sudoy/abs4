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

import abs4.beans.Abs4;
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

			sql = "select day, type, content, cost from details d"
					+" JOIN categories ON d.category_id = categories.id"
					+" ORDER BY d.id";

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			ArrayList<Abs4> list = new ArrayList<>();

			while(rs.next()) {
				Abs4 abs4 = new Abs4(
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
