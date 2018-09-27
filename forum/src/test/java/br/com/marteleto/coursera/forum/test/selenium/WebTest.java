package br.com.marteleto.coursera.forum.test.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import br.com.marteleto.coursera.forum.test.page.CadastroTopicoPage;
import br.com.marteleto.coursera.forum.test.page.CadastroUsuarioPage;
import br.com.marteleto.coursera.forum.test.page.ListarRankingPage;
import br.com.marteleto.coursera.forum.test.page.ListarTopicoPage;
import br.com.marteleto.coursera.forum.test.page.LoginPage;
import br.com.marteleto.coursera.forum.util.Constantes;
import br.com.marteleto.coursera.forum.vo.Topico;
import br.com.marteleto.coursera.forum.vo.Usuario;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebTest {
	private static WebDriver driver;

	@BeforeClass
	public static void beforeClass() {
		URL url = WebTest.class.getResource("/webdriver/chrome/2.41/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", url.getFile());
        driver = new ChromeDriver();
	}
	
	@AfterClass
	public static void afterClass() {
		driver.close();
        driver.quit();
	}
	
	@Before
	public void before() {
		driver.manage().window().maximize();
	}
	
	@Test
	public void test1AutenticarFalha() {
		LoginPage page = PageFactory.initElements(driver, LoginPage.class);
		String falha = page.autenticar("amarteleto", "123456");
		assertEquals(Constantes.MSG_FALHA_AUTENTICACAO, falha);
	}
	
	@Test
	public void test2CadastrarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setLogin("amarteleto");
		usuario.setSenha("123456");
		usuario.setEmail("amarteleto@outlook.com");
		usuario.setNome("Anderson Assis Marteleto");
		CadastroUsuarioPage page = PageFactory.initElements(driver, CadastroUsuarioPage.class);
		String sucesso = page.cadastrar(usuario);
		assertEquals(Constantes.MSG_SUCESSO_SALVAR_USUARIO, sucesso);
	}
	
	@Test
	public void test3AutenticarSucesso() {
		LoginPage page = PageFactory.initElements(driver, LoginPage.class);
		String sucesso = page.autenticar("amarteleto", "123456");
		assertEquals(Constantes.MSG_SUCESSO_AUTENTICACAO, sucesso);
	}
	
	@Test
	public void test4NaoExisteTopico() {
		ListarTopicoPage page = PageFactory.initElements(driver, ListarTopicoPage.class);
		boolean existe = page.semTopicos();
		assertTrue(existe);
	}
	
	@Test
	public void test5CadastrarTopico() {
		Topico topico = new Topico();
		topico.setTitulo("teste topico");
		topico.setConteudo("teste topico");
		CadastroTopicoPage page = PageFactory.initElements(driver, CadastroTopicoPage.class);
		String sucesso = page.cadastrar(topico);
		assertEquals(Constantes.MSG_SUCESSO_SALVAR_TOPICO, sucesso);
	}
	
	@Test
	public void test6ExisteTopico() {
		ListarTopicoPage page = PageFactory.initElements(driver, ListarTopicoPage.class);
		boolean existe = page.semTopicos();
		assertFalse(existe);
		existe = page.existeTopico("teste topico");
		assertTrue(existe);
	}
	
	@Test
	public void test7ExisteRanking() {
		ListarRankingPage page = PageFactory.initElements(driver, ListarRankingPage.class);
		boolean existe = page.semRanking();
		assertFalse(existe);
		existe = page.existeUsuario("Anderson Assis Marteleto");
		assertTrue(existe);
	}
	
}
