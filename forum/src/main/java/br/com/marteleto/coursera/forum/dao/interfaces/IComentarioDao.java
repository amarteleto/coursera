package br.com.marteleto.coursera.forum.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import br.com.marteleto.coursera.forum.vo.Comentario;

public interface IComentarioDao extends Serializable {
	List<Comentario> recuperarTodos(Integer topico);
	void salvar(Comentario comentario);
}
