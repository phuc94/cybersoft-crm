package crm08.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm08.entity.JobEntity;
import services.GroupworkService;

@WebServlet(name = "groupwork", urlPatterns = {"/groupwork", "/groupwork-add", "/groupwork-details"})
public class GroupworkController extends HttpServlet {
	
	private GroupworkService groupworkService = new GroupworkService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();

		switch (servletPath) {
			case "/groupwork": {
				getGroupwork(req, resp);
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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		String name = req.getParameter("name");
		String startDate = req.getParameter("start_date");
		String endDate = req.getParameter("end_date");

		if (servletPath.equals("/groupwork-add")) {
			if (groupworkService.saveRole(name, startDate, endDate)) {
				req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
			} else {
				req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
			}
		} else {
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
		}
	}
	
	private void getGroupwork(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<JobEntity> jobs = groupworkService.findAll();
		req.setAttribute("jobs", jobs);
		req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
	}

}
