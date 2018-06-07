package abs4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abs4.utils.DBUtils;

@WebServlet("/entry.html")
public class EntryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {


		req.getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");


		String day = req.getParameter("day");
		String category_id = req.getParameter("category_id");
		String content = req.getParameter("content");
		String cost = req.getParameter("cost");

		String division = req.getParameter("division");

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBUtils.getConnection();

			String sql = "insert into details(day, category_id, content, cost)"
					+" values(?, ?, ?, ?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, day);
			ps.setString(2, category_id);
			ps.setString(3, content.equals("")? null : content);
			ps.setString(4, cost);

			ps.executeUpdate();

			resp.sendRedirect("index.html");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps);
		}

//		req.getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp").forward(req, resp);
	}
}
