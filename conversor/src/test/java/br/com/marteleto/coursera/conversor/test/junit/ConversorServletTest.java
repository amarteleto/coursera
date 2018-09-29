package br.com.marteleto.coursera.conversor.test.junit;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import br.com.marteleto.coursera.conversor.servlet.ConversorServlet;
import br.com.marteleto.coursera.conversor.test.util.ServletMock;
import br.com.marteleto.coursera.conversor.util.ConversorUtil;

public class ConversorServletTest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Before
	public void before() {
		ServletMock.clear();
	}

	@Test
	public void converterCelsiusParaFahrenheit() throws ServletException, IOException  {
		String conversao = "<span id='resultado'>" + ConversorUtil.converterCelsiusParaFahrenheit(100.0) + "</span>";
		ServletMock.addParameter("cbOpcao", "2");
		ServletMock.addParameter("txValor", "100");
		ConversorServlet conversorServlet = new ConversorServlet();
		conversorServlet.service(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
	    String conteudo = ServletMock.getHttpServletResponse().toString();
	    assertTrue(conteudo.contains(conversao));
	}
	
	@Test
	public void converterFahrenheitParaCelsius() throws ServletException, IOException {
		String conversao = "<span id='resultado'>" + ConversorUtil.converterFahrenheitParaCelsius(212.0) + "</span>";
		ServletMock.addParameter("cbOpcao", "1");
		ServletMock.addParameter("txValor", "212");
		ConversorServlet conversorServlet = new ConversorServlet();
		conversorServlet.service(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
	    String conteudo = ServletMock.getHttpServletResponse().toString();
	    assertTrue(conteudo.contains(conversao));
	}
	
}
