package crm08.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "authenFilter", urlPatterns = {
	"/user*",
	"/role*",
	"/task*",
	"/profile*",
	"/groupwork*",
})
public class AuthenticationFilter implements Filter {
	private static String ADMIN = "ROLE_ADMIN";
	private static String MANAGER = "ROLE_MANAGER";
	private static String USER = "ROLE_USER";

	@Override
	public void doFilter(ServletRequest sevlReq, ServletResponse sevlRes, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) sevlReq;
		HttpServletResponse res = (HttpServletResponse) sevlRes;
		Cookie[] reqCookies = req.getCookies();
		String roleName = "";
		String path = req.getServletPath();
		List<String> allowedRoles = new ArrayList<String>();
		
		if (reqCookies == null) {
			req.getRequestDispatcher("login.jsp").forward(req, res);
		} else {
			for (Cookie cookie : reqCookies) {
				if (cookie.getName() == "role") {
					roleName = cookie.getValue();
				}
			}
		}
		
		if (roleName.isEmpty()) {
			req.getRequestDispatcher("login.jsp").forward(req, res);
		}
		
		switch (path) {
			case "user-add": {
				allowedRoles.add(ADMIN);
				checkRole(roleName, allowedRoles, chain, req, res);
				break;
			}
			case "user-table": {
				allowedRoles.add(ADMIN);
				checkRole(roleName, allowedRoles, chain, req, res);
				break;
			}
			case "user-details": {
				allowedRoles.add(ADMIN);
				checkRole(roleName, allowedRoles, chain, req, res);
				break;
			}
			case "groupwork": {
				allowedRoles.add(MANAGER);
				checkRole(roleName, allowedRoles, chain, req, res);
				break;
			}
			case "groupwork-add": {
				allowedRoles.add(ADMIN);
				checkRole(roleName, allowedRoles, chain, req, res);
				break;
			}
			case "groupwork-details": {
				allowedRoles.add(ADMIN);
				checkRole(roleName, allowedRoles, chain, req, res);
				break;
			}
			case "profile-edit": {
				allowedRoles.add(ADMIN);
				checkRole(roleName, allowedRoles, chain, req, res);
				break;
			}
			case "profile": {
				allowedRoles.add(ADMIN);
				checkRole(roleName, allowedRoles, chain, req, res);
				break;
			}
			case "task": {
				allowedRoles.add(USER);
				checkRole(roleName, allowedRoles, chain, req, res);
				break;
			}
			case "task-add": {
				allowedRoles.add(ADMIN);
				checkRole(roleName, allowedRoles, chain, req, res);
				break;
			}
			case "role": {
				allowedRoles.add(ADMIN);
				checkRole(roleName, allowedRoles, chain, req, res);
				break;
			}
			case "role-add": {
				allowedRoles.add(ADMIN);
				checkRole(roleName, allowedRoles, chain, req, res);
				break;
			}
			case "role-table": {
				allowedRoles.add(ADMIN);
				checkRole(roleName, allowedRoles, chain, req, res);
				break;
			}
			default:
				chain.doFilter(req, res);
				break;
		}
	}
	
	private void checkRole(String role, List<String> allowedRole, FilterChain chain, HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
//		chain.doFilter(req, res);
		if (allowedRole.contains(role)) {
			chain.doFilter(req, res);
		} else {
			req.getRequestDispatcher("login.jsp").forward(req, res);
		}
	}
	
}
