package br.com.marteleto.coursera.conversor.test.selenium;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import br.com.marteleto.coursera.conversor.test.page.IndexPage;
import br.com.marteleto.coursera.selenium.util.WebDriverUtil;

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
		driver.manage().window().maximize(); 
	}
	
	@Test
	public void converterCelsiusParaFahrenheit() {
		IndexPage page = PageFactory.initElements(driver, IndexPage.class);
		Double fahrenheit = page.converterCelsiusParaFahrenheit(100.0);
		assertEquals(Double.valueOf(212), fahrenheit);
	}
	
	@Test
	public void converterFahrenheitParaCelsius() {
		IndexPage page = PageFactory.initElements(driver, IndexPage.class);
		Double celsius = page.converterFahrenheitParaCelsius(212.0);
		assertEquals(Double.valueOf(100), celsius);
	}
}
