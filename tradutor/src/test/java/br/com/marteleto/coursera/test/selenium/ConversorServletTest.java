package br.com.marteleto.coursera.test.selenium;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import br.com.marteleto.coursera.test.page.IndexPage;

public class ConversorServletTest {
	private static WebDriver driver;
	

	@BeforeClass
	public static void beforeClass() {
		URL url = ConversorServletTest.class.getResource("/webdriver/chrome/2.41/chromedriver.exe");
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
		driver.get("http://localhost:8080/tradutor/"); 
	}
	
	@Test
	public void traduzirPalavra1() {
		IndexPage page = PageFactory.initElements(driver, IndexPage.class);
		String traducao = page.traduzir("ola");
		assertEquals("hello", traducao);
	}
	
	@Test
	public void traduzirPalavra2() {
		IndexPage page = PageFactory.initElements(driver, IndexPage.class);
		String traducao = page.traduzir("amarelo");
		assertEquals("yellow", traducao);
	}
	
	@Test
	public void traduzirPalavraNaoExiste() {
		IndexPage page = PageFactory.initElements(driver, IndexPage.class);
		String traducao = page.traduzir("xxxxx");
		assertEquals("xxxxx", traducao);
	}
}
