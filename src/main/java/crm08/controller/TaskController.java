package crm08.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm08.entity.JobEntity;
import crm08.entity.TaskEntity;
import crm08.entity.UserEntity;
import repository.TaskRepo;
import services.GroupworkService;
import services.TaskService;
import services.UserService;

@WebServlet(name = "task", urlPatterns = {"/task", "/task-add"})
public class TaskController extends HttpServlet {
	
	private TaskService taskService = new TaskService();
	private GroupworkService groupworkService = new GroupworkService();
	private UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();

		switch (servletPath) {
			case "/task": {
				getTask(req, resp);
				break;
			}
			case "/task-add": {
				getAddTask(req, resp);
				break;
			}
			default:
				break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		if (servletPath.equals("/task-add")) {
			String task_name = req.getParameter("task_name");
			String user_id = req.getParameter("user_id");
			String job_id = req.getParameter("job_id");
			String start_date = req.getParameter("start_date");
			String end_date = req.getParameter("end_date");

			//TODO
			if(taskService.saveTask(task_name, start_date, end_date, user_id, job_id)) {
				req.getRequestDispatcher("task.jsp").forward(req, resp);
			} else {
				req.getRequestDispatcher("task-add").forward(req, resp);
			}

		}
	}
	
	private void getTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TaskEntity> taskList = taskService.findAll();
		req.setAttribute("tasks", taskList);
		req.getRequestDispatcher("task.jsp").forward(req, resp);
	}
	
	private void getAddTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<JobEntity> jobs = groupworkService.findAll();
		List<UserEntity> userList = userService.getAllUsers();
		req.setAttribute("jobs", jobs);
		req.setAttribute("users", userList);
		req.getRequestDispatcher("task-add.jsp").forward(req, resp);
	}

}
