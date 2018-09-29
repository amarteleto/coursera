package br.com.marteleto.coursera.forum.servlet.topico;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.marteleto.coursera.forum.business.TopicoBusiness;
import br.com.marteleto.coursera.forum.business.interfaces.ITopicoBusiness;
import br.com.marteleto.coursera.forum.util.Constantes;
import br.com.marteleto.coursera.forum.util.WebUtil;
import br.com.marteleto.coursera.forum.vo.Topico;

@WebServlet(value="/topico/listar.slt")
public class ListarTopicoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ITopicoBusiness topicoBusiness = new TopicoBusiness();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Topico> topicos = topicoBusiness.recuperarTodos();
			req.setAttribute("topicos", topicos);
			WebUtil.redirecionarPaginaListarTopico(req, resp);
		} catch (Exception ex) {
			WebUtil.publicarMensagemFalha(req, Constantes.MSG_FALHA_RECUPERAR_TOPICO, ex);
			WebUtil.redirecionarPaginaListarTopico(req, resp);
		}
	}
}
