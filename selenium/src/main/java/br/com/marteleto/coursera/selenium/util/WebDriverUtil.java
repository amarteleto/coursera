package br.com.marteleto.coursera.selenium.util;

import java.io.Serializable;

import org.openqa.selenium.WebDriver;

import br.com.marteleto.coursera.selenium.webdriver.ChromeWebDriver;
import br.com.marteleto.coursera.selenium.webdriver.FirefoxWebDriver;

public class WebDriverUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String firefox = "firefox";
	public static final String chrome = "chrome";
	
	public static WebDriver getWebDriver(String tipo, String versao) {
		WebDriver driver = null;
		if (tipo.equals(firefox)) {
			driver = FirefoxWebDriver.getWebDriver(versao);
		} else if (tipo.equals(chrome)) {
			driver = ChromeWebDriver.getWebDriver(versao);
		}
		return driver;
	}
	
	public static void waitForLoad() {
		waitForLoad(1000);
	}
	
    public static void waitForLoad(Integer milissegundos) {
        try {
			Thread.sleep(milissegundos);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
    }
}