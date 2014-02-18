package connectionWeb;


import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/searchBook")
public class SearchBook extends HttpServlet {
	private String bookOption=null;
	private String libraryName=null;
	private String keyword="";

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String a = request.getParameter("Library");
		if (a.equals("1")) {
			libraryName = "lib1";
		} else if (a.equals("2")) {
			libraryName = "lib2";
		} else if (a.equals("3")) {
			libraryName = "lib3";
		}
		
		String b = request.getParameter("Option");
		if (b.equals("1")) {
			bookOption = "title";
		} else if (b.equals("2")) {
			bookOption = "author";
		} else if (b.equals("3")) {
			bookOption = "callNumber";
		}
		
		
		keyword = (String)(request.getParameter("KeyWord")).trim();
		
		session.setAttribute("libraryName", libraryName);
		session.setAttribute("bookOption", bookOption);
		session.setAttribute("keyword", keyword);
		
		response.sendRedirect("/library/search.jsp");
	}
}