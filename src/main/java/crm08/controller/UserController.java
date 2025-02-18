package crm08.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm08.entity.RoleEntity;
import crm08.entity.UserEntity;
import services.UserService;

@WebServlet(name = "user", urlPatterns = {"/user", "/user-add", "/user-details"})
public class UserController extends HttpServlet {
	
	private UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();

		switch (servletPath) {
			case "/user": {
				getUser(req, resp);
				break;
			}
			case "/user-add": {
				addUser(req, resp);
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		if (servletPath.equals("/user-add")) {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String fullname = req.getParameter("fullname");
			String avatar = req.getParameter("avatar");
			String role_id = req.getParameter("role_id");
			System.out.println(fullname);
			if(userService.saveUser(email, password, fullname, avatar, role_id)) {
				System.out.println("oke");
				req.getRequestDispatcher("user.jsp").forward(req, resp);
			} else {
				req.getRequestDispatcher("user-add.jsp").forward(req, resp);
			}
		} else {
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
		}
	}
	
	
	private void getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<UserEntity> userList = userService.getAllUsers();
		req.setAttribute("users", userList);
		System.out.println(userList.size());
		req.getRequestDispatcher("user.jsp").forward(req, resp);
	}
	
	private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RoleEntity> roleList = new ArrayList<RoleEntity>();
		roleList = userService.getRoles();
		System.out.println(roleList.get(0).getName());
		req.setAttribute("roles", roleList);
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}

}
