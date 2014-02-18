package connectionWeb;


import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/logout")
public class Logout extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		session.setAttribute("Staff", null);

		session.setAttribute("User", null);
		
		session.setAttribute("Barcode", null);
		
		session.setAttribute("People",null);
		
		session.setAttribute("Personal",null);
		
		session.setAttribute("AddBook",null);
		
		session.setAttribute("AddStaff",null);
		
		session.setAttribute("ModifyStaff",null);
		
		session.setAttribute("DeleteStaff",null);
		
		session.setAttribute("ChangePassword",null);
		
		session.setAttribute("libraryName",null);
		
		session.setAttribute("bookOption",null);
		
		session.setAttribute("keyword",null);
		
		response.sendRedirect("/library/index.jsp");
	}
}
