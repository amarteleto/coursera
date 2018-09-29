package br.com.marteleto.coursera.forum.servlet.usuario;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.marteleto.coursera.forum.business.UsuarioBusiness;
import br.com.marteleto.coursera.forum.business.interfaces.IUsuarioBusiness;
import br.com.marteleto.coursera.forum.util.Constantes;
import br.com.marteleto.coursera.forum.util.WebUtil;
import br.com.marteleto.coursera.forum.vo.Usuario;

@WebServlet(value="/usuario/ranking.slt")
public class RankingUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final IUsuarioBusiness usuarioBusiness = new UsuarioBusiness();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Usuario> usuarios = usuarioBusiness.recuperarRanking();
			req.setAttribute("usuarios", usuarios);
			WebUtil.redirecionarPaginaUsuarioRanking(req, resp);
		} catch (Exception ex) {
			WebUtil.publicarMensagemFalha(req, Constantes.MSG_FALHA_RANKING_USUARIO, ex);
			WebUtil.redirecionarPaginaUsuarioRanking(req, resp);
		}
	}
}
