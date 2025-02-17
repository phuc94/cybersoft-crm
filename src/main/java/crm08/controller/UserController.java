package crm08.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "user", urlPatterns = {"/user", "/user-add", "/user-details"})
public class UserController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();

		switch (servletPath) {
			case "/user": {
				req.getRequestDispatcher("user.jsp").forward(req, resp);
				break;
			}
			case "/user-add": {
				req.getRequestDispatcher("user-add.jsp").forward(req, resp);
				break;
			}
			case "/user-details": {
				req.getRequestDispatcher("user-details.jsp").forward(req, resp);
				break;
			}
			default:
				break;
		}
	}

}
