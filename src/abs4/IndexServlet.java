package abs4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
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

		LocalDate now = null;
		if(req.getParameter("now") == null) {
			now = LocalDate.now();
		}else {
			now = LocalDate.parse(req.getParameter("now"));
		}

		LocalDate firstDay = now.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate lastDay = now.with(TemporalAdjusters.lastDayOfMonth());
		LocalDate lastMonthFirstDay = now.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
		LocalDate lastMonthLastDay = now.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

		Connection con = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;
		PreparedStatement ps5 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		ResultSet rs5 = null;

		try {
			con = DBUtils.getConnection();

			// 一覧データ
			String sql1 = "SELECT d.id, d.day, c.type, d.content, d.cost "
					+ "FROM  details d "
					+ "JOIN categories c ON d.category_id = c.id "
					+ "WHERE d.day BETWEEN ? AND ? "
					+ "ORDER BY d.day";

			ps1 = con.prepareStatement(sql1);
			ps1.setString(1, firstDay.toString());
			ps1.setString(2, lastDay.toString());

			rs1 = ps1.executeQuery();

			ArrayList<Detail> list = new ArrayList<>();

			while(rs1.next()) {
				Detail abs4 = new Detail(
						rs1.getInt("id"),
						Utils.date2LocalDate(rs1.getDate("day")),
						rs1.getString("type"),
						rs1.getString("content"),
						rs1.getInt("cost"));

				list.add(abs4);
			}
			req.setAttribute("list", list);


			// 今月の収入
			String sql2 = "SELECT SUM(cost) AS this_month_income "
					+ "FROM details "
					+ "WHERE cost > 0 AND day BETWEEN ? AND ?";
			ps2 = con.prepareStatement(sql2);
			ps2.setString(1, firstDay.toString());
			ps2.setString(2, lastDay.toString());
			rs2 = ps2.executeQuery();
			rs2.next();
			int thisMonthIncome = rs2.getInt("this_month_income");
			req.setAttribute("thisMonthIncome", thisMonthIncome);

			// 今月の支出
			String sql3 = "SELECT SUM(cost) AS this_month_outgo "
					+ "FROM details "
					+ "WHERE cost < 0 AND day BETWEEN ? AND ?";
			ps3 = con.prepareStatement(sql3);
			ps3.setString(1, firstDay.toString());
			ps3.setString(2, lastDay.toString());
			rs3 = ps3.executeQuery();
			rs3.next();
			int thisMonthOutgo = rs3.getInt("this_month_outgo");
			req.setAttribute("thisMonthOutgo", thisMonthOutgo);

			// 収入の先月比
			String sql4 = "SELECT SUM(cost) AS last_month_income "
					+ "FROM details "
					+ "WHERE cost > 0 AND day BETWEEN ? AND ?";
			ps4 = con.prepareStatement(sql4);
			ps4.setString(1, lastMonthFirstDay.toString());
			ps4.setString(2, lastMonthLastDay.toString());
			rs4 = ps4.executeQuery();
			rs4.next();
			int lastMonthIncome = rs4.getInt("last_month_income");
			req.setAttribute("thisMonthIncomeDiff", thisMonthIncome - lastMonthIncome);

			// 支出の先月比
			String sql5 = "SELECT SUM(cost) AS last_month_outgo "
					+ "FROM details "
					+ "WHERE cost < 0 AND day BETWEEN ? AND ?";
			ps5 = con.prepareStatement(sql5);
			ps5.setString(1, lastMonthFirstDay.toString());
			ps5.setString(2, lastMonthLastDay.toString());
			rs5 = ps5.executeQuery();
			rs5.next();
			int lastMonthOutgo = rs5.getInt("last_month_outgo");
			req.setAttribute("thisMonthOutgoDiff", thisMonthOutgo - lastMonthOutgo);

			req.setAttribute("now", now);
			getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}finally {
			DBUtils.close(con, ps1, rs1);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);

	}
}
