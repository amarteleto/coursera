package br.com.marteleto.coursera.forum.test.junit;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;

import org.junit.Test;

import br.com.marteleto.coursera.forum.exception.BusinessException;
import br.com.marteleto.coursera.forum.exception.DaoException;
import br.com.marteleto.coursera.forum.util.ConfigUtil;
import br.com.marteleto.coursera.forum.util.Constantes;

@SuppressWarnings("unused")
public class AllOutrosTest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void validarBusinessException() throws IOException, ServletException {
		BusinessException businessException = new BusinessException("teste");
		businessException = new BusinessException("teste", new RuntimeException("teste"));
		businessException = new BusinessException(new RuntimeException("teste"));
	}
	
	@Test
	public void validarDaoException() throws IOException, ServletException {
		DaoException daoException = new DaoException("teste");
		daoException = new DaoException("teste", new RuntimeException("teste"));
		daoException = new DaoException(new RuntimeException("teste"));
	}
	
	@Test
	public void constantes() {
		ConfigUtil config = new ConfigUtil();
		ConfigUtil.getSeleniumUrl();
		Constantes constantes = new Constantes();
		String parametro = Constantes.USUARIO_LOGADO_PARAMETER;
		parametro = Constantes.MSG_SUCESSO_AUTENTICACAO;
		parametro = Constantes.MSG_SUCESSO_SALVAR_USUARIO;
		parametro = Constantes.MSG_SUCESSO_SALVAR_TOPICO;
		parametro = Constantes.MSG_SUCESSO_SALVAR_COMENTARIO;
		parametro = Constantes.MSG_FALHA_AUTENTICACAO;
		parametro = Constantes.MSG_FALHA_SALVAR_USUARIO;
		parametro = Constantes.MSG_FALHA_SALVAR_TOPICO;
		parametro = Constantes.MSG_FALHA_SALVAR_COMENTARIO;
		parametro = Constantes.MSG_FALHA_ADICIONAR_PONTO_USUARIO;
		parametro = Constantes.MSG_FALHA_RECUPERAR_USUARIO;
		parametro = Constantes.MSG_FALHA_RECUPERAR_RANKING_USUARIO;
		parametro = Constantes.MSG_FALHA_RECUPERAR_TOPICO;
		parametro = Constantes.MSG_FALHA_RECUPERAR_COMENTARIO;
		parametro = Constantes.MSG_FALHA_BUSCAR_TOPICO;
		parametro = Constantes.MSG_FALHA_RANKING_USUARIO;
	}
}
