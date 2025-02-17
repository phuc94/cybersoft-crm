package crm08.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "role", urlPatterns = {"/role-add", "/role-table"})
public class RoleController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();

		switch (servletPath) {
		case "/role-add": {
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
			break;
		}
		case "/role-table": {
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
			break;
		}
		default:
			break;
		}
	}

}
