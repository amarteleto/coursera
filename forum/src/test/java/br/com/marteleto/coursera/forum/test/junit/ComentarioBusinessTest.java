package br.com.marteleto.coursera.forum.test.junit;

import static org.junit.Assert.assertEquals;

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
		List<Comentario> comentarios = comentarioBusiness.recuperarTodos(1);
		assertEquals(Integer.valueOf(1), comentarios.get(0).getId());
	}
	
	@Test
	public void salvar() {
		List<Comentario> comentarios = comentarioBusiness.recuperarTodos(1);
		Integer novaQtd = comentarios.size() + 1;
		Usuario criador = usuarioBusiness.recuperar("amarteleto");
		Integer pontos = criador.getPontos() + Constantes.PONTOS_COMENTARIO;
		Topico topico = topicoBusiness.buscarTopico(1);
		Comentario comentario = new Comentario();
		comentario.setComentario("bla bla bla bla");
		comentario.setCriador(criador);
		comentario.setTopico(topico);
		comentarioBusiness.salvar(comentario);
		List<Comentario> comentariosAtualizado = comentarioBusiness.recuperarTodos(1);
		assertEquals(novaQtd, Integer.valueOf(comentariosAtualizado.size()));
		Usuario criadorAtualizado = usuarioBusiness.recuperar("amarteleto");
		assertEquals(pontos, criadorAtualizado.getPontos());
	}
}
