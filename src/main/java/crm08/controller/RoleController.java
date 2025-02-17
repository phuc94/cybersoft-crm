package crm08.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm08.entity.RoleEntity;
import services.UserService;

@WebServlet(name = "role", urlPatterns = {"/role-add", "/role-table"})
public class RoleController extends HttpServlet {

	UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();

		switch (servletPath) {
			case "/role-add": {
				req.getRequestDispatcher("role-add.jsp").forward(req, resp);
				break;
			}
			case "/role-table": {
				getRole(req, resp);
				break;
			}
			default:
				break;
		}
	}
	
	private void getRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RoleEntity> roleList = userService.getRoles();
		req.setAttribute("roles", roleList);
		req.getRequestDispatcher("role-table.jsp").forward(req, resp);
	}

}
