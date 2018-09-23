package br.com.marteleto.coursera.conversor.util;

import java.io.Serializable;

public class ConversorUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	public static Double converterCelsiusParaFahrenheit(Double celsius) {
		return ((celsius * 9) / 5) + 32;
	}
	
	public static Double converterFahrenheitParaCelsius(Double fahrenheit) {
		return ((fahrenheit - 32) * 5) / 9;
	}
}
