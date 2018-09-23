package br.com.marteleto.coursera.forum.business;

import java.util.List;

import br.com.marteleto.coursera.forum.business.interfaces.IComentarioBusiness;
import br.com.marteleto.coursera.forum.business.interfaces.IUsuarioBusiness;
import br.com.marteleto.coursera.forum.dao.ComentarioDao;
import br.com.marteleto.coursera.forum.dao.interfaces.IComentarioDao;
import br.com.marteleto.coursera.forum.util.Constantes;
import br.com.marteleto.coursera.forum.vo.Comentario;

public class ComentarioBusiness implements IComentarioBusiness {
	private static final long serialVersionUID = 1L;
	private IComentarioDao dao = new ComentarioDao();
	private IUsuarioBusiness usuarioBusiness = new UsuarioBusiness();
	
	@Override
	public List<Comentario> recuperarTodos(Integer topico) {
		return dao.recuperarTodos(topico);
	}
	
	@Override
	public void salvar(Comentario comentario) {
		dao.salvar(comentario);
		usuarioBusiness.adicionarPontos(comentario.getCriador().getLogin(), Constantes.PONTOS_COMENTARIO);
	}
}
