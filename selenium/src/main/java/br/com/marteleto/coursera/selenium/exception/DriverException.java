package br.com.marteleto.coursera.selenium.exception;

public class DriverException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DriverException(String mensagem) {
		super(mensagem);
	}
	
	public DriverException(String mensagem, Exception ex) {
		super(mensagem,ex);
	}
	
	public DriverException(Exception ex) {
		super(ex.getMessage(),ex);
	}
}
