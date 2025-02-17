package crm08.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm08.entity.TaskEntity;
import services.TaskService;

@WebServlet(name = "task", urlPatterns = {"/task", "/task-add"})
public class TaskController extends HttpServlet {
	
	private TaskService taskService = new TaskService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();

		switch (servletPath) {
			case "/task": {
				getTask(req, resp);
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
	
	private void getTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TaskEntity> taskList = taskService.findAll();
		req.setAttribute("tasks", taskList);
		req.getRequestDispatcher("task.jsp").forward(req, resp);
	}

}
