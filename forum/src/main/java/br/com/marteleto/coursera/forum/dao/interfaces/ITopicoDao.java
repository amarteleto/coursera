package br.com.marteleto.coursera.forum.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import br.com.marteleto.coursera.forum.vo.Topico;

public interface ITopicoDao extends Serializable {
	List<Topico> recuperarTodos();
	Topico buscarTopico(Integer id);
	void salvar(Topico topico);
}
