package br.com.marteleto.coursera.selenium.webdriver;

import java.io.Serializable;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;

import br.com.marteleto.coursera.selenium.exception.DriverException;

public class ChromeWebDriver implements Serializable {
	private static final long serialVersionUID = 1L;

	public static ChromeDriver getWebDriver(String versao) {
		carregarDriver(versao);
		return new ChromeDriver();
	}
		
	private static void carregarDriver(String versao) {
		URL url = ChromeWebDriver.class.getClassLoader().getResource("webdriver/chrome/" + versao + "/chromedriver.exe");
		if (url != null) {
			System.setProperty("webdriver.chrome.driver", url.getFile());
		} else {
			throw new DriverException("Versão não encontrada.");
		}
	}
}
