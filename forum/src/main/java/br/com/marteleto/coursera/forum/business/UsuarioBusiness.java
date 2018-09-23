package br.com.marteleto.coursera.forum.business;

import br.com.marteleto.coursera.forum.business.interfaces.IUsuarioBusiness;
import br.com.marteleto.coursera.forum.dao.UsuarioDao;
import br.com.marteleto.coursera.forum.dao.interfaces.IUsuarioDao;
import br.com.marteleto.coursera.forum.util.Constantes;
import br.com.marteleto.coursera.forum.vo.Usuario;

public class UsuarioBusiness implements IUsuarioBusiness {
	private static final long serialVersionUID = 1L;
	private IUsuarioDao dao = new UsuarioDao(); 
	
	@Override
	public void salvar(Usuario usuario) {
		dao.salvar(usuario);
	}

	@Override
	public Usuario recuperar(String login) {
		return dao.recuperar(login, null);
	}
	
	@Override
	public Usuario autenticar(String login, String senha) {
		Usuario usuario = dao.recuperar(login, senha);
		if (usuario == null) {
			throw new RuntimeException(Constantes.MSG_FALHA_AUTENTICACAO);
		}
		return usuario;
	}

	@Override
	public void adicionarPontos(String login, Integer pontos) {
		dao.adicionarPontos(login, pontos);
	}

}
