package abs4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abs4.beans.Detail;
import abs4.utils.DBUtils;
import abs4.utils.Utils;

@WebServlet("/detail.html")
public class DetailServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

		String id = req.getParameter("id");

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBUtils.getConnection();

			String sql = "select details.id, day, type, content, cost from details"
						+" JOIN categories"
						+" ON details.category_id = categories.id"
						+" where details.id = ?";

			ps = con.prepareStatement(sql);

			ps.setString(1, id);

			rs = ps.executeQuery();


			if(!rs.next()) {
				throw new ServletException();
			}

			Detail dl = new Detail(
						rs.getInt("id"),
						Utils.date2LocalDate(rs.getDate("day")),
						rs.getString("type"),
						rs.getString("content"),
						Math.abs(rs.getInt("cost"))
					);

			req.setAttribute("dl", dl);

			req.getServletContext().getRequestDispatcher("/WEB-INF/detail.jsp").forward(req, resp);


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

		req.getServletContext().getRequestDispatcher("/WEB-INF/detail.jsp").forward(req, resp);

	}
}
