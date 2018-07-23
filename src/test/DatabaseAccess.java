package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DatabaseAccess
 */
@WebServlet("/DatabaseAccess")
public class DatabaseAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// JDBC驱动和DB的URL
	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String DB_URL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=study";

	// 登陆数据库的用户名和密码
	static final String USER = "sa";
	static final String PASS = "123qwe";


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String title = "Servlet SqlServer";
		String docType = "<!DOCTYPE html>\n";
		out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>"
				+ "<body bgcolor = \"#f0f0f0\">\n" + "<h1 align = \"center\">" + title + "</h1>\n");

		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("驱动加载成功！");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("驱动加载失败");
		}
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("创建连接成功！");
			stmt = conn.createStatement();
			String sql;

			// 执行SQL语句
			sql = "SELECT * FROM website";
			ResultSet rs = stmt.executeQuery(sql);

			// 输出结果
			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("name");
				String url = rs.getString("url");

				out.println("ID:" + id + "\t");
				out.println("名称:" + name + "\t");
				out.println("url:" + url + "\t");
				out.println("<br />");
			}
			out.println("</body></html>");

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.print("创建连接失败！");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("class-forName异常");
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
