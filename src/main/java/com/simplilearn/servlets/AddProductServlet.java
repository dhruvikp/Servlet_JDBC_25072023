package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.utils.DBUtils;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/addproduct")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer pid = Integer.valueOf(request.getParameter("pid"));
		String pname = request.getParameter("pname");
		Double pprice = Double.valueOf(request.getParameter("pprice"));

		DBUtils dbUtils = new DBUtils();
		Connection connection = dbUtils.getConnection();
		String message = "Product Added successfully!";
		try {
			CallableStatement callStmt = connection.prepareCall("call add_product(?,?,?)");
			callStmt.setInt(1, pid);
			callStmt.setString(2, pname);
			callStmt.setDouble(3, pprice);
			callStmt.executeUpdate();

		} catch (SQLException e) {
			message = "Product failed to added in DB, Please contact administrator";
			e.printStackTrace();
		}
		dbUtils.closeConnection();

		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<p>" + message + "</p>");
		out.println("</body></html>");
	}
}
