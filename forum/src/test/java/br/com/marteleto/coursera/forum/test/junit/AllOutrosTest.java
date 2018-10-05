package br.com.marteleto.coursera.forum.test.junit;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;

import org.junit.Test;

import br.com.marteleto.coursera.forum.exception.BusinessException;
import br.com.marteleto.coursera.forum.exception.DaoException;
import br.com.marteleto.coursera.forum.util.ConfigUtil;
import br.com.marteleto.coursera.forum.util.Constantes;
import br.com.marteleto.coursera.forum.util.WebUtil;

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
	public void creates() throws IOException, ServletException {
		DaoException daoException = new DaoException("teste");
		daoException = new DaoException("teste", new RuntimeException("teste"));
		daoException = new DaoException(new RuntimeException("teste"));
		WebUtil webUtil = new WebUtil();
		ConfigUtil config = new ConfigUtil();
	}
	
	@Test
	public void constantes() {
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
		parametro = WebUtil.URL_JSP_LOGIN;
		parametro = WebUtil.URL_JSP_USUARIO_CADASTRO;
		parametro = WebUtil.URL_JSP_USUARIO_RANKING;
		parametro = WebUtil.URL_JSP_TOPICO_CADASTRO;
		parametro = WebUtil.URL_JSP_LISTAR_TOPICO;
		parametro = WebUtil.URL_JSP_CONSULTAR_TOPICO;
		parametro = WebUtil.URL_SLT_CONSULTAR_TOPICO;
		parametro = WebUtil.URL_SLT_LISTAR_TOPICO;
		parametro = WebUtil.URL_SLT_RANKING_USUARIO;
		parametro = WebUtil.ATRIBUTO_MENSAGEM;
		parametro = WebUtil.ATRIBUTO_MENSAGEM_CLASS;
	}
}
