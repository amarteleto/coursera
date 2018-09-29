package br.com.marteleto.coursera.forum.test.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;

import br.com.marteleto.coursera.forum.business.ComentarioBusiness;
import br.com.marteleto.coursera.forum.business.TopicoBusiness;
import br.com.marteleto.coursera.forum.business.UsuarioBusiness;
import br.com.marteleto.coursera.forum.business.interfaces.IComentarioBusiness;
import br.com.marteleto.coursera.forum.business.interfaces.ITopicoBusiness;
import br.com.marteleto.coursera.forum.business.interfaces.IUsuarioBusiness;
import br.com.marteleto.coursera.forum.util.Constantes;
import br.com.marteleto.coursera.forum.vo.Comentario;
import br.com.marteleto.coursera.forum.vo.Topico;
import br.com.marteleto.coursera.forum.vo.Usuario;

public class ComentarioBusinessTest implements Serializable {
	private static final long serialVersionUID = 1L;
	private static IComentarioBusiness comentarioBusiness = new ComentarioBusiness();
	private static IUsuarioBusiness usuarioBusiness = new UsuarioBusiness();
	private static ITopicoBusiness topicoBusiness = new TopicoBusiness();
	
	@Test
	public void recuperarTodos() {
		List<Topico> lista = topicoBusiness.recuperarTodos();
		assertNotNull(lista);
		Topico topico = lista.get(0);
		List<Comentario> comentarios = comentarioBusiness.recuperarTodos(topico.getId());
		assertNotNull(comentarios);
	}
	
	@Test
	public void salvar() {
		List<Topico> lista = topicoBusiness.recuperarTodos();
		assertNotNull(lista);
		Topico topico = lista.get(0);
		List<Comentario> comentarios = comentarioBusiness.recuperarTodos(topico.getId());
		Integer novaQtd = comentarios.size() + 1;
		Usuario criador = usuarioBusiness.recuperar("junit");
		Integer pontos = criador.getPontos() + Constantes.PONTOS_COMENTARIO;
		Comentario comentario = new Comentario();
		comentario.setConteudo("bla bla bla bla");
		comentario.setCriador(criador);
		comentario.setTopico(topico);
		comentarioBusiness.salvar(comentario);
		List<Comentario> comentariosAtualizado = comentarioBusiness.recuperarTodos(topico.getId());
		assertEquals(novaQtd, Integer.valueOf(comentariosAtualizado.size()));
		Usuario criadorAtualizado = usuarioBusiness.recuperar("junit");
		assertEquals(pontos, criadorAtualizado.getPontos());
	}
}
