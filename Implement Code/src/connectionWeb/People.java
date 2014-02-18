package connectionWeb;


import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/people")
public class People extends HttpServlet {
	private String option="5";
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		option = request.getParameter("option");
		session.setAttribute("People", option);

		response.sendRedirect("/library/people.jsp");
	}
}
