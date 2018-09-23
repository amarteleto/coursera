package br.com.marteleto.coursera.forum.test.selenium;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import br.com.marteleto.coursera.forum.test.page.LoginPage;
import br.com.marteleto.coursera.forum.util.Constantes;

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
		//driver.get("http://localhost:8080/forum/");
	}
	
	@Test
	public void test1AutenticarFalha() {
		LoginPage page = PageFactory.initElements(driver, LoginPage.class);
		String falha = page.autenticar("amarteleto", "123456");
		assertEquals(Constantes.MSG_FALHA_AUTENTICACAO, falha);
	}
	
	@Test
	public void test2CadastrarUsuario() {
		
	}
}
