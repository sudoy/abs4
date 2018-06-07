package abs4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abs4.beans.Detail;
import abs4.utils.DBUtils;
import abs4.utils.Utils;

@WebServlet("/result.html")
public class ResultServlet extends HttpServlet {
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//
//		req.getServletContext().getRequestDispatcher("/WEB-INF/result.jsp").forward(req, resp);
//	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBUtils.getConnection();

			List<String> wheres = new ArrayList<>();
			List<String> params = new ArrayList<>();

			String content = req.getParameter("content");
			if(!content.equals("")) {
				wheres.add("content LIKE ?");
				params.add("%" + content + "%");
			}


			// 一覧データ
			String where = "";
			if(wheres.size() > 0) {
				where = "WHERE " + String.join(" AND ", wheres) + " ";
			}
			String sql = "SELECT d.id, d.day, c.type, d.content, d.cost "
					+ "FROM  details d "
					+ "JOIN categories c ON d.category_id = c.id "
					+ where + "ORDER BY d.day";

			ps = con.prepareStatement(sql);
			for(int i = 0; i < params.size(); i++) {
				ps.setString(i + 1, params.get(i));
			}
			// TODO debug
			System.out.println(ps);
			rs = ps.executeQuery();

			ArrayList<Detail> list = new ArrayList<>();

			while(rs.next()) {
				Detail detail = new Detail(
						rs.getInt("id"),
						Utils.date2LocalDate(rs.getDate("day")),
						rs.getString("type"),
						rs.getString("content"),
						rs.getInt("cost"));

				list.add(detail);
			}

			req.setAttribute("list", list);
			getServletContext().getRequestDispatcher("/WEB-INF/result.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}finally {
			DBUtils.close(con, ps, rs);
		}
	}
}
