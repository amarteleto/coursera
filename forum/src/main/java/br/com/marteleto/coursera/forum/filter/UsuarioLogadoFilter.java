package br.com.marteleto.coursera.forum.filter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.marteleto.coursera.forum.util.WebUtil;

@WebFilter("/*")
public class UsuarioLogadoFilter implements Filter {
	private static final Logger log = Logger.getLogger(UsuarioLogadoFilter.class.getName());
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("UsuarioLogadoFilter init ignorado.");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		WebUtil.limparMensagem(req);
		boolean autenticar = this.autenticar(req);
		if(autenticar){
			WebUtil.redirecionarPaginaLogin(req, res);
		} else {
			chain.doFilter(request, response);
		}
	}
	
	private List<String> getExcecoes() {
		List<String> excecoes = new ArrayList<>();
		excecoes.add("/forum/usuario/login.jsp");
		excecoes.add("/forum/usuario/cadastro.jsp");
		excecoes.add("/forum/usuario/autenticar.slt");
		excecoes.add("/forum/usuario/cadastro.slt");
		excecoes.add("/forum/forum.css");
		excecoes.add("/forum/");
		return excecoes;
	}
	
	private boolean autenticar(HttpServletRequest req) {
		List<String> excecoes = this.getExcecoes();
		String uri = req.getRequestURI();
		HttpSession session = req.getSession(false);
		return (
			(session == null || WebUtil.getUsuarioLogado(req) == null) 
			&& !excecoes.contains(uri)
		);
	}
}
