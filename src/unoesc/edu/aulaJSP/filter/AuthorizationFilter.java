package unoesc.edu.aulaJSP.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import unoesc.edu.aulaJSP.model.Usuario;



public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Usuario user = null;
		HttpSession sess = ((HttpServletRequest) request).getSession(false);

		if (sess != null) {
			user = (Usuario) sess.getAttribute("usuarioLogado");
		}

		HttpServletRequest rqt = (HttpServletRequest) request;
//		if (user == null && (rqt.getRequestURI().indexOf("views/Index.xhtml") <= 0
//				         && rqt.getRequestURI().indexOf("login.xhtml") <= 0)) {
	
			if (user == null && (rqt.getRequestURI().indexOf("login.xhtml") <= 0) 
							 && (rqt.getRequestURI().indexOf("Usuario.xhtml") <= 0)) {	
			String contextPath = rqt.getContextPath();

			((HttpServletResponse) response).sendRedirect(contextPath + "/system/login.xhtml");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}
}
