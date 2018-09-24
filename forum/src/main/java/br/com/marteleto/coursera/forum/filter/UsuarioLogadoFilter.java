package br.com.marteleto.coursera.forum.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.marteleto.coursera.forum.util.WebUtil;
import br.com.marteleto.coursera.forum.vo.Usuario;

@WebFilter("/*")
public class UsuarioLogadoFilter implements Filter {

	private ServletContext context;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String loginPage = "login.jsp";
		String loginSlt = "autenticar.slt";
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		HttpSession session = req.getSession(false);
		boolean autenticar = false;
		if(session == null){
			if (!(uri.endsWith(loginPage) || uri.endsWith(loginSlt))) {
				autenticar = true;
			}
		} else {
			Usuario usuario = WebUtil.getUsuarioLogado(req);
			if (usuario == null && !(uri.endsWith(loginPage) || uri.endsWith(loginSlt))) {
				autenticar = true;
			}
		}
		this.context.getContextPath();
		if(autenticar){
			res.sendRedirect(loginPage);
		} else {
			chain.doFilter(request, response);
		}
		
		
	}
}
