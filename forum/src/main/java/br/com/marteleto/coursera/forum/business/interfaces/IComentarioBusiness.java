package br.com.marteleto.coursera.forum.business.interfaces;

import java.io.Serializable;
import java.util.List;

import br.com.marteleto.coursera.forum.vo.Comentario;

public interface IComentarioBusiness extends Serializable {
	List<Comentario> recuperarTodos(Integer topico);
	void salvar(Comentario comentario);
}
