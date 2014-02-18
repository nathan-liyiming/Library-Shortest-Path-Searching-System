package connectionWeb;


import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import util.MySQLConnection;


import java.sql.*;


@WebServlet("/addBook")
public class AddBook extends HttpServlet{

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		response.setContentType("text/html");

		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String barcode = request.getParameter("barcode");
		String status = request.getParameter("status");
		String callNumber = request.getParameter("callNumber");
		String nameOfShelf = request.getParameter("nameOfShelf");
		String libraryName = request.getParameter("libraryName");
		String copy = request.getParameter("copy");
		
		
		Connection con = MySQLConnection.connection();
		
		try {
			Statement state = con.createStatement();
				String query = "INSERT INTO book VALUES('" + title + "','" + author +"','"
						+ publisher +"','" + barcode +"','" + status +"','" + callNumber +"','" + nameOfShelf +"','" + libraryName +"','" + Integer.parseInt(copy) +"')";
				
				state.executeUpdate(query);
				state.close();
				con.close();
				session.setAttribute("AddBook", "Yes");
		} catch (Exception e) {
			session.setAttribute("AddBook", "No");
		}
		response.sendRedirect("/library/book.jsp");
	}
}
