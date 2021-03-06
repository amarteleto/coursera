package br.com.marteleto.coursera.forum.servlet.topico;

import java.io.IOException;

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
import br.com.marteleto.coursera.forum.vo.Usuario;

@WebServlet(value="/topico/cadastro.slt")
public class CadastroTopicoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ITopicoBusiness topicoBusiness = new TopicoBusiness();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String titulo = req.getParameter("txTitulo");
			String conteudo = req.getParameter("txConteudo");
			Usuario criador = WebUtil.getUsuarioLogado(req);
			Topico topico = new Topico();
			topico.setTitulo(titulo);
			topico.setConteudo(conteudo);
			topico.setCriador(criador);
			topicoBusiness.salvar(topico);
			WebUtil.publicarMensagemSucesso(req, Constantes.MSG_SUCESSO_SALVAR_TOPICO);
			WebUtil.redirecionarServletListarTopico(req, resp);
		} catch (Exception ex) {
			WebUtil.publicarMensagemFalha(req, Constantes.MSG_FALHA_SALVAR_TOPICO, ex);
			WebUtil.redirecionarPaginaUsuarioCadastro(req, resp);
		}
	}
}
