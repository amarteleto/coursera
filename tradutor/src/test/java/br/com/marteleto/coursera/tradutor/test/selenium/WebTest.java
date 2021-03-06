package br.com.marteleto.coursera.tradutor.test.selenium;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import br.com.marteleto.coursera.selenium.util.WebDriverUtil;
import br.com.marteleto.coursera.tradutor.test.page.IndexPage;

public class WebTest implements Serializable {
	private static final long serialVersionUID = 1L;
	private static WebDriver driver;
	

	@BeforeClass
	public static void beforeClass() {
		driver = WebDriverUtil.getWebDriver(WebDriverUtil.CHROME, "2.41");
	}
	
	@AfterClass
	public static void afterClass() {
		driver.close();
        driver.quit();
	}
	
	@Before
	public void before() {
		driver.manage().window().maximize();; 
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
