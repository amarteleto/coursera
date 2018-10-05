package br.com.marteleto.coursera.selenium.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.Serializable;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import br.com.marteleto.coursera.selenium.exception.DriverException;
import br.com.marteleto.coursera.selenium.util.WebDriverUtil;
import br.com.marteleto.coursera.selenium.webdriver.ChromeWebDriver;
import br.com.marteleto.coursera.selenium.webdriver.FirefoxWebDriver;

@SuppressWarnings("unused")
public class WebDriverUtilTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Test
	public void carregarDriverFalha() {
		WebDriver driver = WebDriverUtil.getWebDriver("", "");
		assertNull(driver);
	}
	
	@Test
	public void carregarFirefoxSucesso() {
		WebDriver driver = WebDriverUtil.getWebDriver(WebDriverUtil.FIREFOX, "0.16");
		assertNotNull(driver);
		driver.close();
        driver.quit();
	}
	
	@Test(expected=DriverException.class)
	public void carregarFirefoxFalha() {
		WebDriverUtil.getWebDriver(WebDriverUtil.FIREFOX, "0.17");
	}
	
	@Test
	public void carregarChromeSucesso() {
		WebDriver driver = WebDriverUtil.getWebDriver(WebDriverUtil.CHROME, "2.41");
		assertNotNull(driver);
		driver.close();
        driver.quit();
	}
	
	@Test(expected=DriverException.class)
	public void carregarChromeFalha() {
		WebDriverUtil.getWebDriver(WebDriverUtil.CHROME, "2.42");
	}
	
	@Test
	public void sleepSucesso() {
		WebDriverUtil.waitForLoad();
	}
	
	@Test
	public void creates() {
		DriverException exception = new DriverException(new RuntimeException());
		exception = new DriverException("");
		exception = new DriverException("", new RuntimeException());
		ChromeWebDriver chromeWebDriver = new ChromeWebDriver();
		FirefoxWebDriver firefoxWebDriver = new FirefoxWebDriver();
		WebDriverUtil webDriverUtil = new WebDriverUtil();
	}
}
