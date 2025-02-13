package crm08.filter;

import java.io.IOException;

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
	"/user-add",
	"/user-details",
	"/user-table",
	"/role-add",
	"/role-table",
	"/task",
	"/task-add",
	"/profile",
	"/profile-edit",
	"/groupwork",
	"/groupwork-add",
	"/groupwork-details",
})
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest sevlReq, ServletResponse sevlRes, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) sevlReq;
		HttpServletResponse res = (HttpServletResponse) sevlRes;
		Cookie[] reqCookies = req.getCookies();
		String roleName = "";
		String path = req.getServletPath();
		
		if (reqCookies == null) {
			req.getRequestDispatcher("login.jsp").forward(req, res);
		}
		
		for (Cookie cookie : reqCookies) {
			if (cookie.getName() == "role") {
				roleName = cookie.getValue();
			}
		}
		
		if (roleName.equals("")) {
			req.getRequestDispatcher("login.jsp").forward(req, res);
		}
		
		switch (path) {
			case "user-add": {
				if (roleName.equals("ROLE_ADMIN")) {
					chain.doFilter(req, res);
				} else {
					req.getRequestDispatcher("login.jsp").forward(req, res);
				}
				break;
			}
			case "user-table": {
				if (roleName.equals("ROLE_ADMIN")) {
					chain.doFilter(req, res);
				} else {
					req.getRequestDispatcher("login.jsp").forward(req, res);
				}
				break;
			}
			default:
				break;
		}
	}
	
}
