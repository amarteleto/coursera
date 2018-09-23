package br.com.marteleto.coursera.forum.test.junit;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;

import br.com.marteleto.coursera.forum.business.TopicoBusiness;
import br.com.marteleto.coursera.forum.business.UsuarioBusiness;
import br.com.marteleto.coursera.forum.business.interfaces.ITopicoBusiness;
import br.com.marteleto.coursera.forum.business.interfaces.IUsuarioBusiness;
import br.com.marteleto.coursera.forum.util.Constantes;
import br.com.marteleto.coursera.forum.vo.Topico;
import br.com.marteleto.coursera.forum.vo.Usuario;

public class TopicoBusinessTest implements Serializable {
	private static final long serialVersionUID = 1L;
	private static ITopicoBusiness topicoBusiness = new TopicoBusiness();
	private static IUsuarioBusiness usuarioBusiness = new UsuarioBusiness();
	
	@Test
	public void recuperarTodos() {
		List<Topico> topicos = topicoBusiness.recuperarTodos();
		assertEquals(Integer.valueOf(1), topicos.get(0).getId());
	}
	
	@Test
	public void salvar() {
		List<Topico> topicos = topicoBusiness.recuperarTodos();
		Integer novaQtd = topicos.size() + 1; 
		Usuario criador = usuarioBusiness.recuperar("amarteleto");
		Integer pontos = criador.getPontos() + Constantes.PONTOS_TOPICO;
		Topico topico = new Topico();
		topico.setTitulo("JUnit Test Dinamico");
		topico.setConteudo("JUnit Test Dinamico");
		topico.setCriador(criador);
		topicoBusiness.salvar(topico);
		List<Topico> topicosAtualizado = topicoBusiness.recuperarTodos();
		assertEquals(novaQtd, Integer.valueOf(topicosAtualizado.size()));
		Usuario criadorAtualizado = usuarioBusiness.recuperar("amarteleto");
		assertEquals(pontos, criadorAtualizado.getPontos());
	}
}
