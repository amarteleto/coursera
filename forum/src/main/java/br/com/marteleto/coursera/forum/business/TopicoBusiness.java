package br.com.marteleto.coursera.forum.business;

import java.util.List;

import br.com.marteleto.coursera.forum.business.interfaces.ITopicoBusiness;
import br.com.marteleto.coursera.forum.business.interfaces.IUsuarioBusiness;
import br.com.marteleto.coursera.forum.dao.TopicoDao;
import br.com.marteleto.coursera.forum.dao.interfaces.ITopicoDao;
import br.com.marteleto.coursera.forum.util.Constantes;
import br.com.marteleto.coursera.forum.vo.Topico;

public class TopicoBusiness implements ITopicoBusiness {
	private static final long serialVersionUID = 1L;
	private ITopicoDao dao = new TopicoDao();
	private IUsuarioBusiness usuarioBusiness = new UsuarioBusiness();
	
	@Override
	public List<Topico> recuperarTodos() {
		return dao.recuperarTodos();
	}

	@Override
	public Topico buscarTopico(Integer id) {
		return dao.buscarTopico(id);
	}

	@Override
	public void salvar(Topico topico) {
		dao.salvar(topico);
		usuarioBusiness.adicionarPontos(topico.getCriador().getLogin(), Constantes.PONTOS_TOPICO);
	}

}
