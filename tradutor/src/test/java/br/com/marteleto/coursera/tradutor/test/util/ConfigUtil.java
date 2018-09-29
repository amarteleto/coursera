package br.com.marteleto.coursera.tradutor.test.util;

import java.io.Serializable;

public class ConfigUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String SELENIUM_URL = "http://localhost:8080/tradutor";
	
    public static String getSeleniumUrl() {
       return SELENIUM_URL;
    }
}
