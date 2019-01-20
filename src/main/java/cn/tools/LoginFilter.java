package cn.tools;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter extends HttpServlet implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest sRequest, ServletResponse sResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) sRequest;
		HttpServletResponse response = (HttpServletResponse) sResponse;
		HttpSession session = request.getSession();
		String url = request.getServletPath();
		String contextPath = request.getContextPath();
		if (url.endsWith(".jsp")) {
			// 获取当前session
			Object adminPo =  session.getAttribute("admin");
			if ((url.endsWith("/login.jsp"))) {
				// 判断session
				if (adminPo != null) {
					response.sendRedirect(contextPath + "/admin/index.jsp");
					return;
				}
			} else {
		
				// 判断session
				if (adminPo == null) {
					response.sendRedirect(contextPath + "/admin/login.jsp");
					return;
				}
			}
		}
		filterChain.doFilter(sRequest, sResponse);
	}

	public void init(FilterConfig arg0) throws ServletException {

	}
}