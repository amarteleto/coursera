package br.com.marteleto.coursera.forum.business.interfaces;

import java.io.Serializable;
import java.util.List;

import br.com.marteleto.coursera.forum.vo.Topico;

public interface ITopicoBusiness extends Serializable {
	List<Topico> recuperarTodos();
	Topico buscarTopico(Integer id);
	void salvar(Topico topico);
}
