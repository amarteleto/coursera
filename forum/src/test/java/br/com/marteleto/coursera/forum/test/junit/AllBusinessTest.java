package br.com.marteleto.coursera.forum.test.junit;

import java.io.Serializable;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.marteleto.coursera.forum.util.ConfigUtil;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	UsuarioBusinessTest.class,
	TopicoBusinessTest.class,
	ComentarioBusinessTest.class,
})
public class AllBusinessTest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@BeforeClass
	public static void before() throws Exception {
		ConfigUtil.definirConfiguracao("forumTest.properties", true);
		FlatXmlDataFileLoader flatXmlDataFileLoader = new FlatXmlDataFileLoader();
		JdbcDatabaseTester jdbcDatabaseTester = new JdbcDatabaseTester(ConfigUtil.getDatabaseClass(), ConfigUtil.getDatabaseUrl());
		jdbcDatabaseTester.setDataSet(flatXmlDataFileLoader.load("/database.xml"));
		jdbcDatabaseTester.onSetup();
	}
}
