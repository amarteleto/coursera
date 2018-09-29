package br.com.marteleto.coursera.dbunit.exception;

public class DaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DaoException(String mensagem) {
		super(mensagem);
	}
	
	public DaoException(String mensagem, Exception ex) {
		super(mensagem,ex);
	}
	
	public DaoException(Exception ex) {
		super(ex.getMessage(),ex);
	}
}
