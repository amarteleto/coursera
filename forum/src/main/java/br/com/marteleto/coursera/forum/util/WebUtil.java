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
	public static String URL_JSP_USUARIO_RANKING = "/usuario/ranking.jsp";
	public static String URL_JSP_TOPICO_CADASTRO = "/topico/cadastro.jsp";
	public static String URL_JSP_LISTAR_TOPICO = "/topico/listar.jsp";
	public static String URL_JSP_CONSULTAR_TOPICO = "/topico/consultar.jsp";

	public static String URL_SLT_CONSULTAR_TOPICO = "/topico/consultar.slt";
	public static String URL_SLT_LISTAR_TOPICO = "/topico/listar.slt";
	public static String URL_SLT_RANKING_USUARIO = "/usuario/ranking.slt";
	
	private static void redirecionar(HttpServletRequest req, HttpServletResponse resp, String endereco) throws ServletException, IOException {
		req.getRequestDispatcher(endereco).forward(req, resp);
	}
	private static void publicarMensagem(HttpServletRequest req, String mensagem, String tipo) {
		req.setAttribute("mensagem",mensagem);
		req.setAttribute("mensagem_class",tipo);
		//req.getSession().setAttribute("mensagem",mensagem);
		//req.getSession().setAttribute("mensagem_class",tipo);
	}
	public static void publicarMensagemFalha(HttpServletRequest req, String mensagem) {
		WebUtil.publicarMensagem(req,mensagem,"falha");
	}
	public static void publicarMensagemSucesso(HttpServletRequest req, String mensagem) {
		WebUtil.publicarMensagem(req,mensagem,"sucesso");
	}
	public static void setUsuarioLogado(HttpServletRequest req, Usuario usuario) {
		req.getSession().setAttribute(Constantes.USUARIO_LOGADO_PARAMETER, usuario);
	}
	public static Usuario getUsuarioLogado(HttpServletRequest req) {
		return (Usuario) req.getSession().getAttribute(Constantes.USUARIO_LOGADO_PARAMETER);
	}
	public static void limparMensagem(HttpServletRequest req) {
		req.getSession().removeAttribute("mensagem");
		req.getSession().removeAttribute("mensagem_class");
	}
	//PAGINA
	public static void redirecionarPaginaLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil.redirecionar(req, resp, WebUtil.URL_JSP_LOGIN);
	}
	public static void redirecionarPaginaUsuarioCadastro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil.redirecionar(req, resp, WebUtil.URL_JSP_USUARIO_CADASTRO);
	}
	public static void redirecionarPaginaUsuarioRanking(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil.redirecionar(req, resp, WebUtil.URL_JSP_USUARIO_RANKING);
	}
	public static void redirecionarPaginaTopicoCadastro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil.redirecionar(req, resp, WebUtil.URL_JSP_TOPICO_CADASTRO);
	}
	public static void redirecionarPaginaListarTopico(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil.redirecionar(req, resp, WebUtil.URL_JSP_LISTAR_TOPICO);
	}
	public static void redirecionarPaginaConsultarTopico(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil.redirecionar(req, resp, WebUtil.URL_JSP_CONSULTAR_TOPICO);
	}
	//SERVLET
	public static void redirecionarServletListarTopico(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil.redirecionar(req, resp, WebUtil.URL_SLT_LISTAR_TOPICO);
	}
	public static void redirecionarServletRankingUsuario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil.redirecionar(req, resp, WebUtil.URL_SLT_RANKING_USUARIO);
	}
	public static void redirecionarServletConsultarTopico(HttpServletRequest req, HttpServletResponse resp, Integer topico) throws ServletException, IOException {
		WebUtil.redirecionar(req, resp, WebUtil.URL_SLT_CONSULTAR_TOPICO + "?id=" + topico);
	}
}
