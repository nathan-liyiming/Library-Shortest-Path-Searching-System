package connectionWeb;


import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/personal")
public class Personal extends HttpServlet{
	private static String option="5";
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		option = request.getParameter("option");
		
		session.setAttribute("Personal", option);

		response.sendRedirect("/library/personal.jsp");
	}
}
