package br.com.marteleto.coursera.conversor.test.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.marteleto.coursera.conversor.util.ConversorUtil;

@SuppressWarnings("unused")
public class ConversorUtilTest {

	@Test
	public void converterCelsiusParaFahrenheit() {
		Double fahrenheit = ConversorUtil.converterCelsiusParaFahrenheit(100.0);
		assertEquals(Double.valueOf(212), fahrenheit);
	}
	
	@Test
	public void converterFahrenheitParaCelsius() {
		Double fahrenheit = ConversorUtil.converterFahrenheitParaCelsius(212.0);
		assertEquals(Double.valueOf(100), fahrenheit);
	}
	
	@Test
	public void constantes() {
		ConversorUtil conversorUtil = new ConversorUtil();
	}
}
