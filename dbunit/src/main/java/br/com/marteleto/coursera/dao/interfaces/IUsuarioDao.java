package br.com.marteleto.coursera.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import br.com.marteleto.coursera.vo.Usuario;

public interface IUsuarioDao extends Serializable {
	//insere um novo usu�rio no banco de dados
	void inserir(Usuario u);
	
	//recupera o usu�rio pelo seu login
	Usuario recuperar(String login);
	   
	//adiciona os pontos para o usu�rio no banco
	void adicionarPontos(String login, int pontos);
	   
	//retorna a lista de usu�rios ordenada por pontos (maior primeiro)
	List<Usuario> ranking();
}
