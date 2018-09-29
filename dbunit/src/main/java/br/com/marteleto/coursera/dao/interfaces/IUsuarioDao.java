package br.com.marteleto.coursera.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import br.com.marteleto.coursera.vo.Usuario;

public interface IUsuarioDao extends Serializable {
	//insere um novo usuário no banco de dados
	void inserir(Usuario u);
	
	//recupera o usuário pelo seu login
	Usuario recuperar(String login);
	   
	//adiciona os pontos para o usuário no banco
	void adicionarPontos(String login, int pontos);
	   
	//retorna a lista de usuários ordenada por pontos (maior primeiro)
	List<Usuario> ranking();
}
