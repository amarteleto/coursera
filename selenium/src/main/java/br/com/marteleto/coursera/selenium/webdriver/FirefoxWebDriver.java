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
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		return desiredCapabilities;
	}
	
	private static FirefoxProfile getProfile() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 0);
		/*
		Integer proxyPort = 6060;
		String proxyUrl = "proxy-1dn.mb";
		profile.setPreference("signon.autologin.proxy", true);
		profile.setPreference("network.proxy.type", 1);
		profile.setPreference("network.proxy.share_proxy_settings", true);
		profile.setPreference("network.negotiate-auth.trusted-uris", "www.google.com");
		profile.setPreference("network.http.phishy-userpass-length", 255);
		profile.setPreference("network.proxy.http", proxyUrl);
		profile.setPreference("network.proxy.http_port", proxyPort);
		profile.setPreference("network.proxy.ftp", proxyUrl);
		profile.setPreference("network.proxy.ftp_port", proxyPort);
		profile.setPreference("network.proxy.socks", proxyUrl);
		profile.setPreference("network.proxy.socks_port", proxyPort);
		profile.setPreference("network.proxy.ssl", proxyUrl);
		profile.setPreference("network.proxy.ssl_port", proxyPort);
		profile.setPreference("network.proxy.backup.ftp", proxyUrl);
		profile.setPreference("network.proxy.backup.ftp_port", proxyPort);
		profile.setPreference("network.proxy.backup.socks", proxyUrl);
		profile.setPreference("network.proxy.backup.socks_port", proxyPort);
		profile.setPreference("network.proxy.backup.ssl", proxyUrl);
		profile.setPreference("network.proxy.backup.ssl_port", proxyPort);
		*/
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