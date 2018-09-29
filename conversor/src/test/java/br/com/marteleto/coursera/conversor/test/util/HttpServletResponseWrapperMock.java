package br.com.marteleto.coursera.conversor.test.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class HttpServletResponseWrapperMock extends HttpServletResponseWrapper {

	  private StringWriter sw = new StringWriter(1024);

	  public HttpServletResponseWrapperMock(HttpServletResponse response) {
	    super(response);
	  }

	  public PrintWriter getWriter() throws IOException {
	    return new PrintWriter(sw);
	  }

	  public ServletOutputStream getOutputStream() throws IOException {
	    throw new UnsupportedOperationException();
	  }

	  public String toString() {
	    return sw.toString();
	  }

}
