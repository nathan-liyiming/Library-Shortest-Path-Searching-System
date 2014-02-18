package connectionWeb;


import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;

@WebServlet("/addOperationList")
public class AddOperationList extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		String barcode = request.getParameter("barcode");

		HttpSession s = request.getSession();
		ArrayList<String> list=(ArrayList<String>)s.getAttribute("Barcode");
		if(list==null){
			list=new ArrayList<String>();
		}
		list.add(barcode);
		s.setAttribute("Barcode",list);
		

		response.sendRedirect("/library/search.jsp");
	}
}
