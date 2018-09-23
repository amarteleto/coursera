package br.com.marteleto.coursera.forum.dao.interfaces;

import java.io.Serializable;

import br.com.marteleto.coursera.forum.vo.Usuario;

public interface IUsuarioDao extends Serializable {
	void salvar(Usuario usuario);
	Usuario recuperar(String login, String senha);
	void adicionarPontos(String login, Integer pontos);
}
