package br.com.marteleto.coursera.dbunit.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import br.com.marteleto.coursera.dbunit.vo.Usuario;

public interface IUsuarioDao extends Serializable {
	void inserir(Usuario u);
	Usuario recuperar(String login);
	void adicionarPontos(String login, int pontos);
	List<Usuario> ranking();
}
