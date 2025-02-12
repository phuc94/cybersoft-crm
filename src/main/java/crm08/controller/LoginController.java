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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm08.config.MysqlConfig;
import crm08.entity.UserEntity;

@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String remember = req.getParameter("remember-me");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		List<UserEntity> listUser = new ArrayList<UserEntity>();

		String query = "SELECT u.email, u.password \n"
				+ "FROM users u\n"
				+ "WHERE u.email = ? AND u.password = ?";
		Connection connection = MysqlConfig.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				UserEntity user = new UserEntity();
				user.setEmail(result.getString("email"));
				System.out.println(result.getString("email"));
				listUser.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(listUser.size() > 0) {
			System.out.println("OKEEEEEEEEEEEEEEEE");
			req.getRequestDispatcher("index.html").forward(req, resp);
		} else {
			System.out.println("FAILLLLLLLLLLLLLLLLLL");
		}
		
			
	}

}
