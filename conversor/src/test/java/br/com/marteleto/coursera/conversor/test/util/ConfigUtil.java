package br.com.marteleto.coursera.conversor.test.util;

import java.io.Serializable;

public class ConfigUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String SELENIUM_URL = "http://localhost:8080/conversor";
	
    public static String getSeleniumUrl() {
       	return SELENIUM_URL;
    }
}
