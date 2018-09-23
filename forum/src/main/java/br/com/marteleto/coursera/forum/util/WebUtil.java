package br.com.marteleto.coursera.forum.util;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.marteleto.coursera.forum.vo.Usuario;

public class WebUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	public static String URL_JSP_LOGIN = "/login.jsp";
	public static String URL_JSP_USUARIO_CADASTRO = "/usuario/cadastro.jsp";
	public static String URL_JSP_TOPICO_CADASTRO = "/topico/cadastro.jsp";
	public static String URL_JSP_LISTAR_TOPICO = "/topico/listar.jsp";

	public static String URL_SLT_LISTAR_TOPICO = "/topico/listar.slt";
	
	public static void publicarMensagemFalha(HttpServletRequest req, String mensagem) {
		req.setAttribute("mensagem", mensagem);
		req.setAttribute("mensagem_class", "falha");
	}
	
	public static void publicarMensagemSucesso(HttpServletRequest req, String mensagem) {
		req.setAttribute("mensagem", mensagem);
		req.setAttribute("mensagem_class", "sucesso");
	}
	
	public static void setUsuarioLogado(HttpServletRequest req, Usuario usuario) {
		req.getSession().setAttribute(Constantes.USUARIO_LOGADO_PARAMETER, usuario);
	}
	
	public static Usuario getUsuarioLogado(HttpServletRequest req) {
		return (Usuario) req.getSession().getAttribute(Constantes.USUARIO_LOGADO_PARAMETER);
	}
	
	public static void redirecionarPagina(HttpServletRequest req, HttpServletResponse resp, String pagina) throws ServletException, IOException {
		req.getRequestDispatcher(pagina).forward(req, resp);
	}
	
	public static void redirecionarPaginaLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil.redirecionarPagina(req, resp, WebUtil.URL_JSP_LOGIN);
	}
	
	public static void redirecionarPaginaUsuarioCadastro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil.redirecionarPagina(req, resp, WebUtil.URL_JSP_USUARIO_CADASTRO);
	}
	
	public static void redirecionarPaginaTopicoCadastro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil.redirecionarPagina(req, resp, WebUtil.URL_JSP_TOPICO_CADASTRO);
	}
	
	public static void redirecionarPaginaListarTopico(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil.redirecionarPagina(req, resp, WebUtil.URL_JSP_LISTAR_TOPICO);
	}
	
	public static void redirecionarServletListarTopico(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil.redirecionarPagina(req, resp, WebUtil.URL_SLT_LISTAR_TOPICO);
	}
}
