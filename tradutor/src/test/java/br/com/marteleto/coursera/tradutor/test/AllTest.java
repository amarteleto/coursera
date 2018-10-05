package br.com.marteleto.coursera.tradutor.test;

import java.io.Serializable;
import java.util.logging.Logger;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

import br.com.marteleto.coursera.tradutor.test.junit.TradutorServletTest;
import br.com.marteleto.coursera.tradutor.test.junit.TradutorUtilTest;
import br.com.marteleto.coursera.tradutor.test.selenium.WebTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TradutorUtilTest.class,
	TradutorServletTest.class,
	WebTest.class,
})
public class AllTest implements Serializable {
	private static final long serialVersionUID = 1L;
	protected final transient Logger logger = Logger.getLogger(AllTest.class.getName());
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(AllTest.class);
	    for (Failure failure : result.getFailures()) {
	      System.out.println(failure.toString());
	    }
	}
}
