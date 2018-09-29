package br.com.marteleto.coursera.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.marteleto.coursera.dao.UsuarioDao;
import br.com.marteleto.coursera.vo.Usuario;

public class UsuarioDaoTest {
	
	private static UsuarioDao usuarioDao;
	private static JdbcDatabaseTester jdbcDatabaseTester;
	private static String DATABASE_URL = "jdbc:derby:memory:coursera";
	private static String DATABASE_CLASS = "org.apache.derby.jdbc.EmbeddedDriver";
	
	@BeforeClass
	public static void before() throws Exception {
		usuarioDao = new UsuarioDao(DATABASE_CLASS,DATABASE_URL);
		FlatXmlDataFileLoader flatXmlDataFileLoader = new FlatXmlDataFileLoader();
		jdbcDatabaseTester = new JdbcDatabaseTester(DATABASE_CLASS, DATABASE_URL);
		jdbcDatabaseTester.setDataSet(flatXmlDataFileLoader.load("/database.xml"));
		jdbcDatabaseTester.onSetup();
	}

	@Test
	public void inserirUsuario() {
		Usuario usuario = new Usuario();
		usuario.setLogin("junittest");
		usuario.setEmail("junittest@junittest.com.br");
		usuario.setNome("Junit Test");
		usuario.setSenha("123456");
		usuario.setPontos(5);
		usuarioDao.inserir(usuario);
	}
	
	@Test
	public void recuperarUsuario() {
		Usuario usuario = usuarioDao.recuperar("amarteleto");
		assertEquals("amarteleto@outlook.com", usuario.getEmail());		
	}
	
	@Test
	public void adicionarPontosUsuario() {
		String login = "amarteleto";
		usuarioDao.adicionarPontos(login, 15);
		Usuario usuario = usuarioDao.recuperar(login);
		assertEquals(Integer.valueOf(25), usuario.getPontos());		
	}
	
	@Test
	public void rankingUsuario() {
		List<Usuario> usuarios = usuarioDao.ranking();
		assertFalse(usuarios.isEmpty());
		assertEquals("amarteleto", usuarios.get(0).getLogin());
	}
}
