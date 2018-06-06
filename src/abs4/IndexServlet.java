package abs4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//
//		Connection con = null;
//		PreparedStatement ps = null;
//		String sql = null;
//		ResultSet rs = null;
//
//		try {
//			con = DBUtils.getConnection();
//
//			sql = "select day, category_id, content, cost from details";
//
//			ps = con.prepareStatement(sql);
//
//			rs = ps.executeQuery();
//
//		} catch (Exception e) {
//			throw new ServletException();
//		}finally {
//			DBUtils.close(con, ps, rs);
//		}

		req.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);

	}
}
