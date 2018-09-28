package br.com.marteleto.coursera.forum.servlet.topico;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.marteleto.coursera.forum.business.TopicoBusiness;
import br.com.marteleto.coursera.forum.business.interfaces.ITopicoBusiness;
import br.com.marteleto.coursera.forum.util.WebUtil;
import br.com.marteleto.coursera.forum.vo.Topico;

@WebServlet(value="/topico/consultar.slt")
public class ConsultarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ITopicoBusiness topicoBusiness = new TopicoBusiness();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Integer id = Integer.valueOf(req.getParameter("id"));
			Topico topico = topicoBusiness.buscarTopico(id);
			req.setAttribute("topico", topico);
			WebUtil.redirecionarPaginaConsultarTopico(req, resp);
		} catch (Exception ex) {
			WebUtil.publicarMensagemFalha(req, ex.getMessage());
			WebUtil.redirecionarPaginaConsultarTopico(req, resp);
		}
	}
}
