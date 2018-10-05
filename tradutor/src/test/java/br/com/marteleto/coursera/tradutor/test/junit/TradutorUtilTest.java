package br.com.marteleto.coursera.tradutor.test.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import br.com.marteleto.coursera.tradudor.util.TradutorUtil;

@SuppressWarnings("unused")
public class TradutorUtilTest {

	@Test
	public void traduzirNulo() {
		String traducao = TradutorUtil.traduzir(null);
		assertNull(traducao);
	}
	
	@Test
	public void traduzirVazio() {
		String traducao = TradutorUtil.traduzir("");
		assertNull(traducao);
	}
	
	@Test
	public void traduzirPalavraNaoExiste() {
		String traducao = TradutorUtil.traduzir("xxxxxx");
		assertEquals("xxxxxx", traducao);
	}
	
	@Test
	public void traduzirPalavra() {
		String traducao = TradutorUtil.traduzir("ola");
		assertEquals("hello", traducao);
	}
	
	@Test
	public void create() {
		TradutorUtil util = new TradutorUtil();
	}
}
