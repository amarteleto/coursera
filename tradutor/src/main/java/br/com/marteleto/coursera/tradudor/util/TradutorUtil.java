package br.com.marteleto.coursera.tradudor.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TradutorUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(TradutorUtil.class.getName());
	
	private static Properties properties;
	
	public static String traduzir(String chave) {
		String resultado = null;
		if (chave != null && !chave.trim().equals("")) {
			if (properties == null) {
				try {
					properties = new Properties();
					properties.load(TradutorUtil.class.getClassLoader().getResourceAsStream("traducoes.properties"));
				} catch (IOException ex) {
					log.log(Level.SEVERE, ex.getMessage(), ex);
				}
			}
			resultado = properties.getProperty(chave);
			if (resultado == null || resultado.trim().equals("")) {
				resultado = chave;
			}
		}
		return resultado;
	}
}
