package crm08.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "task", urlPatterns = {"/task", "/task-add"})
public class TaskController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();

		switch (servletPath) {
		case "/task": {
			req.getRequestDispatcher("task.jsp").forward(req, resp);
			break;
		}
		case "/task-add": {
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
			break;
		}
		default:
			break;
		}
	}

}
