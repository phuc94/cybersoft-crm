package crm08.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm08.config.MysqlConfig;
import crm08.entity.UserEntity;
import services.UserService;

@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String remember = req.getParameter("remember-me");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		UserService userService = new UserService();
		List<UserEntity> listUser = userService.authenticateUser(email, password);
		
		if(listUser.size() > 0) {
			if (remember != null) {
				Cookie ckEmail = new Cookie("email", email);
				Cookie ckPassword = new Cookie("password", password);
				resp.addCookie(ckPassword);
				resp.addCookie(ckEmail);
			}
			Cookie ckRole = new Cookie("role", listUser.get(0).getRole().getName());
			resp.addCookie(ckRole);

			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} else {
			req.setAttribute("message", "Login failed!");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
			
	}

}
