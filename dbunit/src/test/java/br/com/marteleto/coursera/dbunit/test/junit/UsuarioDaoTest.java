package br.com.marteleto.coursera.dbunit.test.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;

import br.com.marteleto.coursera.dbunit.dao.UsuarioDao;
import br.com.marteleto.coursera.dbunit.exception.DaoException;
import br.com.marteleto.coursera.dbunit.vo.Usuario;

@SuppressWarnings("unused")
public class UsuarioDaoTest implements Serializable {
	private static final long serialVersionUID = 1L;
	private static UsuarioDao usuarioDao;
	private static JdbcDatabaseTester jdbcDatabaseTester;
	private static String DATABASE_URL = "jdbc:derby:memory:coursera";
	private static String DATABASE_CLASS = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final Logger log = Logger.getLogger(UsuarioDaoTest.class.getName());
	
	@BeforeClass
	public static void before() throws Exception {
		usuarioDao = new UsuarioDao(DATABASE_CLASS,DATABASE_URL);
		FlatXmlDataFileLoader flatXmlDataFileLoader = new FlatXmlDataFileLoader();
		jdbcDatabaseTester = new JdbcDatabaseTester(DATABASE_CLASS, DATABASE_URL);
		jdbcDatabaseTester.setDataSet(flatXmlDataFileLoader.load("/database.xml"));
		jdbcDatabaseTester.onSetup();
	}

	@Test
	public void inserirUsuarioSucesso() {
		Usuario usuario = new Usuario();
		usuario.setLogin("junittest");
		usuario.setEmail("junittest@junittest.com.br");
		usuario.setNome("Junit Test");
		usuario.setSenha("123456");
		usuario.setPontos(5);
		usuarioDao.inserir(usuario);
	}
	
	@Test(expected=DaoException.class)
	public void inserirUsuarioFalha() {
		Usuario usuario = new Usuario();
		usuario.setLogin("amarteleto");
		usuario.setEmail("amarteleto@outlook.com");
		usuario.setNome("Anderson Marteleto");
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
	public void adicionarPontosUsuarioSucesso() {
		String login = "amarteleto";
		usuarioDao.adicionarPontos(login, 15);
		Usuario usuario = usuarioDao.recuperar(login);
		assertEquals(Integer.valueOf(25), usuario.getPontos());		
	}
	
	@Test(expected=DaoException.class)
	public void adicionarPontosUsuarioFalha() {
		String login = "amarteletoxxxxx";
		usuarioDao.adicionarPontos(login, 15);
	}
	
	@Test
	public void rankingUsuario() {
		List<Usuario> usuarios = usuarioDao.ranking();
		assertFalse(usuarios.isEmpty());
		assertEquals("amarteleto", usuarios.get(0).getLogin());
	}
	
	@Test
	public void validarDaoException() throws IOException, ServletException {
		DaoException daoException = new DaoException("teste");
		daoException = new DaoException("teste", new RuntimeException("teste"));
		daoException = new DaoException(new RuntimeException("teste"));
	}
	
	@Test
	public void validarFalhaDao() {
		try {
			usuarioDao = new UsuarioDao(null, null);
			fail("falha UsuarioDao(null, null)");
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}
		try {
			usuarioDao = new UsuarioDao(DATABASE_CLASS, null);
			fail("falha UsuarioDao(DATABASE_CLASS, null)");
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}
	}
}
