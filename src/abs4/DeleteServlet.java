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

@WebServlet("/delete.html")
public class DeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

		String id = req.getParameter("id");

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBUtils.getConnection();

			String sql = "delete from details where id = ?";

			ps = con.prepareStatement(sql);

			ps.setString(1, id);

			System.out.println(ps);
			ps.executeUpdate();

			resp.sendRedirect("index.html");

		} catch (Exception e) {
			throw new ServletException();
		}finally {
			DBUtils.close(con, ps);
		}


	}

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//	}
}
