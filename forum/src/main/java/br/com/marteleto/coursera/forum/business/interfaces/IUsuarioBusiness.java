package br.com.marteleto.coursera.forum.business.interfaces;

import java.io.Serializable;
import java.util.List;

import br.com.marteleto.coursera.forum.vo.Usuario;

public interface IUsuarioBusiness extends Serializable {
	void salvar(Usuario usuario);
	Usuario recuperar(String login);
	void adicionarPontos(String login, Integer pontos);
	Usuario autenticar(String login, String senha);
	List<Usuario> recuperarRanking();
}
