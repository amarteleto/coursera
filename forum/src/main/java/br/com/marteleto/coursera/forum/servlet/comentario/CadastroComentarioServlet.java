package br.com.marteleto.coursera.forum.servlet.comentario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.marteleto.coursera.forum.business.ComentarioBusiness;
import br.com.marteleto.coursera.forum.business.TopicoBusiness;
import br.com.marteleto.coursera.forum.business.interfaces.IComentarioBusiness;
import br.com.marteleto.coursera.forum.business.interfaces.ITopicoBusiness;
import br.com.marteleto.coursera.forum.util.Constantes;
import br.com.marteleto.coursera.forum.util.WebUtil;
import br.com.marteleto.coursera.forum.vo.Comentario;
import br.com.marteleto.coursera.forum.vo.Topico;
import br.com.marteleto.coursera.forum.vo.Usuario;

@WebServlet(value="/comentario/cadastro.slt")
public class CadastroComentarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final IComentarioBusiness comentarioBusiness = new ComentarioBusiness();
	private final ITopicoBusiness topicoBusiness = new TopicoBusiness();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Integer idTopico = Integer.valueOf(req.getParameter("txTopico").trim());
			Topico topico = topicoBusiness.buscarTopico(idTopico);
			String texto = req.getParameter("txComentario");
			Usuario criador = WebUtil.getUsuarioLogado(req);
			Comentario comentario = new Comentario();
			comentario.setConteudo(texto);
			comentario.setCriador(criador);
			comentario.setTopico(topico);
			comentarioBusiness.salvar(comentario);
			WebUtil.publicarMensagemSucesso(req, Constantes.MSG_SUCESSO_SALVAR_COMENTARIO);
			WebUtil.redirecionarServletConsultarTopico(req, resp);
		} catch (Exception ex) {
			WebUtil.publicarMensagemFalha(req, Constantes.MSG_FALHA_SALVAR_COMENTARIO, ex);
			WebUtil.redirecionarServletConsultarTopico(req, resp);
		}
	}
}
