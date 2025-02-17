package crm08.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "groupwork", urlPatterns = {"/groupwork", "/groupwork-add", "/groupwork-details"})
public class GroupworkController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();

		switch (servletPath) {
		case "/groupwork": {
			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
			break;
		}
		case "/groupwork-add": {
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
			break;
		}
		case "/groupwork-details": {
			req.getRequestDispatcher("groupwork-details.jsp").forward(req, resp);
			break;
		}
		default:
			break;
		}
	}

}
