package abs4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import abs4.utils.DBUtils;

@WebServlet("/entry.html")
public class EntryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();

		String day = req.getParameter("day");
		String categoryId = req.getParameter("category_id");
		String content = req.getParameter("content");
		String cost = req.getParameter("cost");
		String division = req.getParameter("division");

		List<String> errors = validate(day, categoryId, content, cost, division);

		if(errors.size() > 0) {
			req.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp").forward(req, resp);
			return;
		}

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBUtils.getConnection();

			String sql = "insert into details(day, category_id, content, cost)"
					+" values(?, ?, ?, ?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, day);
			ps.setString(2, categoryId);
			ps.setString(3, content.equals("")? null : content);
			ps.setString(4, division.equals("minus") ? "-" + cost : cost);

			ps.executeUpdate();

			// 成功メッセージ
			List<String> successes = new ArrayList<>();
			successes.add("登録しました。");
			session.setAttribute("successes", successes);

			resp.sendRedirect("index.html");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps);
		}
	}

	private List<String> validate(String day, String categoryId, String content, String cost, String division) {
		List<String> errors = new ArrayList<>();

		// dayの必須入力チェック
		if(day.equals("")) {
			errors.add("日付は必須入力です。");
		}

		// 日付の形式チェック（YYYY/MM/DD）
		if(!day.equals("")) {
			try {
				LocalDate.parse(day, DateTimeFormatter.ofPattern("uuuu/MM/dd")
						.withResolverStyle(ResolverStyle.STRICT));
			}catch(Exception e) {
				errors.add("期限は「YYYY/MM/DD」の形式で入力してください。");
			}
		}

		// カテゴリーの必須入力チェック（ゼロ以外）
		if(Integer.parseInt(categoryId) == 0 ) {
			errors.add("カテゴリーを選択してください。");
		}

		// 金額の必須入力チェック
		if(cost.equals("")) {
			errors.add("金額を入力してください。");
		}

		// 金額が正の値かチェック
		if(cost != "" && Integer.parseInt(cost) < 0) {
			errors.add("金額は正の値で入力してください。");
		}

		//金額欄の数値チェック
		return errors;
	}
}
