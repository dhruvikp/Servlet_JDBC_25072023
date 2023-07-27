package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.utils.DBUtils;

/**
 * Servlet implementation class ListProductsServlet
 */
@WebServlet("/listProducts")
public class ListProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListProductsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		pw.println("<html><body>");
		pw.println("<table>");

		pw.println("<tr>");
		pw.println("<th>Product ID</th>");
		pw.println("<th>Product Name</th>");
		pw.println("<th>Product Price</th>");
		pw.println("<th>Product Date</th>");
		pw.println("</tr>");

		try {
			DBUtils dbUtils = new DBUtils();
			Connection connection = dbUtils.getConnection();
			
			
			
			Statement stmt = connection.createStatement();
			
//			PreparedStatement preparedStmt = connection.prepareStatement("select * from eproduct where productName = ?");
//			preparedStmt.setString(1, "HP Laptop");
			
//			CallableStatement callableStmt =  connection.prepareCall("call add_product(?, ?, ?)");
//			callableStmt.setInt(1, 6);
//			callableStmt.setString(2, "Sample ProductName");
//			callableStmt.setDouble(3, 15000);
//			
//			
//			callableStmt.executeUpdate();
			
			ResultSet rs = stmt.executeQuery("select * from eproduct");

			while (rs.next()) {
				Integer productId = rs.getInt(1);
				String productName = rs.getString(2);
				Double productPrice = rs.getDouble(3);
				Date productDate = rs.getDate(4);

				pw.println("<tr>");
				pw.println("<td>" + productId + "</td>");
				pw.println("<td>" + productName + "</td>");
				pw.println("<td>" + productPrice + "</td>");
				pw.println("<td>" + productDate + "</td>");
				pw.println("</tr>");

			}

			dbUtils.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		pw.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
