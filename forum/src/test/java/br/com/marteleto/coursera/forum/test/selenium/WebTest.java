package br.com.marteleto.coursera.forum.test.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.Calendar;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import br.com.marteleto.coursera.forum.test.page.CadastroTopicoPage;
import br.com.marteleto.coursera.forum.test.page.CadastroUsuarioPage;
import br.com.marteleto.coursera.forum.test.page.ConsultarTopicoPage;
import br.com.marteleto.coursera.forum.test.page.ListarRankingPage;
import br.com.marteleto.coursera.forum.test.page.ListarTopicoPage;
import br.com.marteleto.coursera.forum.test.page.LoginPage;
import br.com.marteleto.coursera.forum.util.ConfigUtil;
import br.com.marteleto.coursera.forum.util.Constantes;
import br.com.marteleto.coursera.forum.vo.Topico;
import br.com.marteleto.coursera.forum.vo.Usuario;
import br.com.marteleto.coursera.selenium.util.WebDriverUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebTest implements Serializable {
	private static final long serialVersionUID = 1L;
	private static WebDriver driver;
	private static Integer pontos = 0;

	@BeforeClass
	public static void beforeClass() {
		driver = WebDriverUtil.getWebDriver(ConfigUtil.getSeleniumWebdriverType(),ConfigUtil.getSeleniumWebdriverVersion());
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
		String falha = page.autenticar("xxx", "xxx");
		assertEquals(Constantes.MSG_FALHA_AUTENTICACAO, falha);
	}
	
	@Test
	public void test2CadastrarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setLogin(String.valueOf(Calendar.getInstance().getTimeInMillis()));
		usuario.setSenha("123456");
		usuario.setEmail("selenium@selenium.com");
		usuario.setNome("Usuario selenium");
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
	public void test4VerificarRanking() {
		ListarRankingPage page = PageFactory.initElements(driver, ListarRankingPage.class);
		WebTest.pontos = page.buscarPontosPorLogin("amarteleto");
	}
	
	@Test
	public void test4CadastrarTopico() {
		Topico topico = new Topico();
		topico.setTitulo("teste topico");
		topico.setConteudo("teste topico");
		CadastroTopicoPage page = PageFactory.initElements(driver, CadastroTopicoPage.class);
		String sucesso = page.cadastrar(topico);
		assertEquals(Constantes.MSG_SUCESSO_SALVAR_TOPICO, sucesso);
		WebTest.pontos += Constantes.PONTOS_TOPICO;
	}
	
	@Test
	public void test5ExisteTopico() {
		ListarTopicoPage page = PageFactory.initElements(driver, ListarTopicoPage.class);
		boolean existe = page.semTopicos();
		assertFalse(existe);
		existe = page.existeTopico("teste topico");
		assertTrue(existe);
		page.acessarPrimeiroTopico();
	}
	
	@Test
	public void test6CriarComentario() {
		ListarTopicoPage listarTopicoPage = PageFactory.initElements(driver, ListarTopicoPage.class);
		Integer topico = listarTopicoPage.buscarIdPrimeiroTopico();
		ConsultarTopicoPage consultarTopicoPage = PageFactory.initElements(driver, ConsultarTopicoPage.class);
		consultarTopicoPage.acessarTopico(topico);
		Integer qtd = consultarTopicoPage.buscarQtdComentarios();
		Integer qtdNova = qtd + 1;
		consultarTopicoPage.adicionarComentario("teste");
		qtd = consultarTopicoPage.buscarQtdComentarios();
		assertEquals(qtdNova, qtd);
		WebTest.pontos += Constantes.PONTOS_COMENTARIO;
	}
	
	@Test
	public void test8VerificarRanking() {
		ListarRankingPage page = PageFactory.initElements(driver, ListarRankingPage.class);
		boolean existe = page.existeRanking();
		assertTrue(existe);
		Integer pontos = page.buscarPontosPorLogin("amarteleto");
		assertEquals(WebTest.pontos, pontos);
	}
}
