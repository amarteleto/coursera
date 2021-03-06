package br.com.marteleto.coursera.forum.test;

import java.io.Serializable;
import java.util.logging.Logger;

import org.junit.BeforeClass;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

import br.com.marteleto.coursera.forum.test.junit.AllBusinessTest;
import br.com.marteleto.coursera.forum.test.junit.AllOutrosTest;
import br.com.marteleto.coursera.forum.test.junit.AllServletTest;
import br.com.marteleto.coursera.forum.test.selenium.WebTest;
import br.com.marteleto.coursera.forum.util.ConfigUtil;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AllBusinessTest.class,
	AllServletTest.class,
	AllOutrosTest.class,
	WebTest.class,
})
public class AllTest implements Serializable {
	private static final long serialVersionUID = 1L;
	protected final transient Logger logger = Logger.getLogger(AllTest.class.getName());
	
	@BeforeClass
	public static void beforeClass() {
		ConfigUtil.definirConfiguracao("forumTest.properties");
	}
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(AllTest.class);
	    for (Failure failure : result.getFailures()) {
	      System.out.println(failure.toString());
	    }
	}
}
