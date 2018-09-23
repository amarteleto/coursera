package br.com.marteleto.coursera.forum.test.junit;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;

import org.junit.Test;

import br.com.marteleto.coursera.forum.business.UsuarioBusiness;
import br.com.marteleto.coursera.forum.business.interfaces.IUsuarioBusiness;
import br.com.marteleto.coursera.forum.vo.Usuario;

public class UsuarioBusinessTest implements Serializable {
	private static final long serialVersionUID = 1L;
	private static IUsuarioBusiness business = new UsuarioBusiness();
	
	@Test
	public void salvarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setLogin("junittest");
		usuario.setEmail("junittest@junittest.com.br");
		usuario.setSenha("123456");
		business.salvar(usuario);
	}
	
	@Test
	public void recuperarUsuario() {
		Usuario usuario = business.recuperar("amarteleto");
		assertEquals("amarteleto@outlook.com", usuario.getEmail());
	}
	
	@Test
	public void autenticarUsuario() {
		Usuario usuario = business.autenticar("amarteleto","123456");
		assertEquals("amarteleto@outlook.com", usuario.getEmail());
	}
	
	@Test
	public void adicionarPontos() {
		business.adicionarPontos("amarteleto", 10);
		Usuario usuario = business.recuperar("amarteleto");
		assertEquals(Integer.valueOf(10), usuario.getPontos());
	}
}
