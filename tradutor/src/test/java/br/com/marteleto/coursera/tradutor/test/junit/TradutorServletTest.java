package br.com.marteleto.coursera.tradutor.test.junit;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import br.com.marteleto.coursera.tradudor.servlet.TradutorServlet;
import br.com.marteleto.coursera.tradudor.util.TradutorUtil;
import br.com.marteleto.coursera.tradutor.test.util.ServletMock;

public class TradutorServletTest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Before
	public void before() {
		ServletMock.clear();
	}

	@Test
	public void traduzirSucesso() throws IOException, ServletException, SAXException {
		String traducao = "<span id='resultado'>" + TradutorUtil.traduzir("ola") + "</span>";
		ServletMock.addParameter("txChave", "ola");
	    TradutorServlet tradutorServlet = new TradutorServlet();
	    tradutorServlet.service(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
	    String conteudo = ServletMock.getHttpServletResponse().toString();
	    assertTrue(conteudo.contains(traducao));
	}
	
	@Test
	public void traduzirFalha() throws IOException, ServletException, SAXException {
		String traducao = "<span id='resultado'>" + TradutorUtil.traduzir("xxxxxxxxxx") + "</span>";
		ServletMock.addParameter("txChave", "xxxxxxxxxx");
	    TradutorServlet tradutorServlet = new TradutorServlet();
	    tradutorServlet.service(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
	    String conteudo = ServletMock.getHttpServletResponse().toString();
	    assertTrue(conteudo.contains(traducao));
	}
	
}
