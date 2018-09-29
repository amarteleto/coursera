package br.com.marteleto.coursera.forum.test.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

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
		usuario.setLogin(String.valueOf(Calendar.getInstance().getTimeInMillis()));
		usuario.setEmail("junittest@junittest.com.br");
		usuario.setSenha("123456");
		business.salvar(usuario);
	}
	
	@Test
	public void recuperarUsuario() {
		Usuario usuario = business.recuperar("junit");
		assertEquals("junit@junit.com", usuario.getEmail());
	}
	
	@Test
	public void autenticarUsuario() {
		Usuario usuario = business.autenticar("junit","123456");
		assertEquals("junit@junit.com", usuario.getEmail());
	}
	
	@Test
	public void adicionarPontos() {
		Usuario usuario = business.recuperar("junit");
		Integer pontos = usuario.getPontos();
		Integer pontosNovo = pontos + 10;
		business.adicionarPontos("junit", 10);
		usuario = business.recuperar("junit");
		assertEquals(pontosNovo, usuario.getPontos());
	}
	
	@Test
	public void recuperarRanking() {
		List<Usuario> usuarios = business.recuperarRanking();
		assertNotNull(usuarios);
	}
}
