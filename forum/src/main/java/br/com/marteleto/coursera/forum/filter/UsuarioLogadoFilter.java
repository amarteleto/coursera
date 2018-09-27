package br.com.marteleto.coursera.forum.filter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import br.com.marteleto.coursera.forum.vo.Usuario;

@WebFilter("/*")
public class UsuarioLogadoFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		List<String> excecoes = new ArrayList<>();
		excecoes.add("/forum/usuario/login.jsp");
		excecoes.add("/forum/usuario/cadastro.jsp");
		excecoes.add("/forum/usuario/autenticar.slt");
		excecoes.add("/forum/usuario/cadastro.slt");
		excecoes.add("/forum/forum.css");
		excecoes.add("/forum/");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		WebUtil.limparMensagem(req);
		String uri = req.getRequestURI();
		HttpSession session = req.getSession(false);
		boolean autenticar = false;
		if(session == null && !excecoes.contains(uri)){
			autenticar = true;
		} else {
			Usuario usuario = WebUtil.getUsuarioLogado(req);
			if (usuario == null && !excecoes.contains(uri)) {
				autenticar = true;
			}
		}
		if(autenticar){
			WebUtil.redirecionarPaginaLogin(req, res);
		} else {
			chain.doFilter(request, response);
		}
		
		
	}
}
