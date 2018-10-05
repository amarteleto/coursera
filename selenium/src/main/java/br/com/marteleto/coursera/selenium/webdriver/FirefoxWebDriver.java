package br.com.marteleto.coursera.selenium.webdriver;

import java.io.Serializable;
import java.net.URL;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import br.com.marteleto.coursera.selenium.exception.DriverException;

public class FirefoxWebDriver implements Serializable {
	private static final long serialVersionUID = 1L;

	public static FirefoxDriver getWebDriver(String versao) {
		carregarDriver(versao);
		FirefoxOptions options = new FirefoxOptions(getCapabilities());
		options.setProfile(getProfile());
		return new FirefoxDriver(options);
	}
	
	private static DesiredCapabilities getCapabilities() {
		return new DesiredCapabilities();
	}
	
	private static FirefoxProfile getProfile() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 0);
		return profile;
	}
	
	private static void carregarDriver(String versao) {
		URL url = FirefoxWebDriver.class.getClassLoader().getResource("webdriver/firefox/" + versao + "/geckodriver.exe");
		if (url != null) {
			System.setProperty("webdriver.gecko.driver", url.getFile());
		} else {
			throw new DriverException("Versão não encontrada.");
		}
	}
}