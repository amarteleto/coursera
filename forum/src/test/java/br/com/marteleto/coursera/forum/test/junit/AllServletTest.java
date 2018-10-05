package br.com.marteleto.coursera.forum.test.junit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import br.com.marteleto.coursera.forum.business.TopicoBusiness;
import br.com.marteleto.coursera.forum.business.UsuarioBusiness;
import br.com.marteleto.coursera.forum.business.interfaces.ITopicoBusiness;
import br.com.marteleto.coursera.forum.business.interfaces.IUsuarioBusiness;
import br.com.marteleto.coursera.forum.filter.UsuarioLogadoFilter;
import br.com.marteleto.coursera.forum.listener.ContextListener;
import br.com.marteleto.coursera.forum.servlet.comentario.CadastroComentarioServlet;
import br.com.marteleto.coursera.forum.servlet.topico.CadastroTopicoServlet;
import br.com.marteleto.coursera.forum.servlet.topico.ConsultarTopicoServlet;
import br.com.marteleto.coursera.forum.servlet.topico.ListarTopicoServlet;
import br.com.marteleto.coursera.forum.servlet.usuario.AutenticacaoUsuarioServlet;
import br.com.marteleto.coursera.forum.servlet.usuario.CadastroUsuarioServlet;
import br.com.marteleto.coursera.forum.servlet.usuario.RankingUsuarioServlet;
import br.com.marteleto.coursera.forum.test.util.ServletMock;
import br.com.marteleto.coursera.forum.util.ConfigUtil;
import br.com.marteleto.coursera.forum.util.Constantes;
import br.com.marteleto.coursera.forum.util.WebUtil;
import br.com.marteleto.coursera.forum.vo.Topico;
import br.com.marteleto.coursera.forum.vo.Usuario;

@SuppressWarnings({"rawtypes","unchecked"})
public class AllServletTest implements Serializable {
	private static final long serialVersionUID = 1L;
	private static IUsuarioBusiness usuarioBusiness = new UsuarioBusiness();
	private static ITopicoBusiness topicoBusiness = new TopicoBusiness();
	
	@Before
	public void before() {
		ServletMock.clear();
		adicionarUsuarioLogado();
	}
	
	private void adicionarUsuarioLogado() {
		List<Usuario> usuarios = usuarioBusiness.recuperarRanking();
		assertNotNull(usuarios);
		ServletMock.addSessionAttribute(Constantes.USUARIO_LOGADO_PARAMETER, usuarios.get(0));
	}
	
	@Test
	public void autenticarSucesso() throws IOException, ServletException {
		ServletMock.addParameter("txLogin", "junit");
		ServletMock.addParameter("txSenha", "123456");
	    AutenticacaoUsuarioServlet autenticacaoServlet = new AutenticacaoUsuarioServlet();
		autenticacaoServlet.service(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
		String mensagem = (String) ServletMock.getHttpServletRequest().getAttribute(WebUtil.ATRIBUTO_MENSAGEM);
		assertEquals(Constantes.MSG_SUCESSO_AUTENTICACAO, mensagem);
	}
	
	@Test
	public void autenticarFalha() throws IOException, ServletException {
		ConfigUtil.definirConfiguracao(null);
		ServletMock.addParameter("txLogin", "junit");
		ServletMock.addParameter("txSenha", "xxxx");
	    AutenticacaoUsuarioServlet autenticacaoServlet = new AutenticacaoUsuarioServlet();
		autenticacaoServlet.service(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
		String mensagem = (String) ServletMock.getHttpServletRequest().getAttribute(WebUtil.ATRIBUTO_MENSAGEM);
		assertEquals(Constantes.MSG_FALHA_AUTENTICACAO, mensagem);
		ConfigUtil.definirConfiguracao("forumTest.properties");
	}
	
	@Test
	public void cadastroSucesso() throws IOException, ServletException {
		ServletMock.addParameter("txLogin", String.valueOf(Calendar.getInstance().getTimeInMillis()));
		ServletMock.addParameter("txEmail", "junit@junit.com.br");
		ServletMock.addParameter("txSenha", "123456");
		ServletMock.addParameter("txNome", "JUnit Test");
		CadastroUsuarioServlet cadastroUsuarioServlet = new CadastroUsuarioServlet();
		cadastroUsuarioServlet.service(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
		String mensagem = (String) ServletMock.getHttpServletRequest().getAttribute(WebUtil.ATRIBUTO_MENSAGEM);
		assertEquals(Constantes.MSG_SUCESSO_SALVAR_USUARIO, mensagem);
	}
	
	@Test
	public void cadastroUsuarioFalha() throws IOException, ServletException {
		ConfigUtil.definirConfiguracao(null);
		ServletMock.addParameter("txLogin", "junit");
		ServletMock.addParameter("txEmail", "junit@junit.com.br");
		ServletMock.addParameter("txSenha", "123456");
		ServletMock.addParameter("txNome", "JUnit Test");
		CadastroUsuarioServlet cadastroUsuarioServlet = new CadastroUsuarioServlet();
		cadastroUsuarioServlet.service(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
		String mensagem = (String) ServletMock.getHttpServletRequest().getAttribute(WebUtil.ATRIBUTO_MENSAGEM);
		assertEquals(Constantes.MSG_FALHA_SALVAR_USUARIO, mensagem);
		ConfigUtil.definirConfiguracao("forumTest.properties");
	}
	
	@Test
	public void rankingUsuarioSucesso() throws IOException, ServletException {
		RankingUsuarioServlet rankingUsuarioServlet = new RankingUsuarioServlet();
		rankingUsuarioServlet.service(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
		List<Usuario> usuarios = (List) ServletMock.getHttpServletRequest().getAttribute("usuarios");
		assertNotNull(usuarios);
	}
	
	@Test
	public void rankingUsuarioFalha() throws IOException, ServletException {
		ConfigUtil.definirConfiguracao(null);
		RankingUsuarioServlet rankingUsuarioServlet = new RankingUsuarioServlet();
		rankingUsuarioServlet.service(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
		String mensagem = (String) ServletMock.getHttpServletRequest().getAttribute(WebUtil.ATRIBUTO_MENSAGEM);
		assertEquals(Constantes.MSG_FALHA_RANKING_USUARIO, mensagem);
		ConfigUtil.definirConfiguracao("forumTest.properties");
	}
	
	@Test
	public void cadastroTopicoSucesso() throws IOException, ServletException {
		ServletMock.addParameter("txTitulo", "junit topico test");
		ServletMock.addParameter("txConteudo", "junit topico test");
		CadastroTopicoServlet cadastroTopicoServlet = new CadastroTopicoServlet();
		cadastroTopicoServlet.service(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
		String mensagem = (String) ServletMock.getHttpServletRequest().getAttribute(WebUtil.ATRIBUTO_MENSAGEM);
		assertEquals(Constantes.MSG_SUCESSO_SALVAR_TOPICO, mensagem);
	}
	
	@Test
	public void cadastroTopicoFalha() throws IOException, ServletException {
		ConfigUtil.definirConfiguracao(null);
		ServletMock.addParameter("txTitulo", "junit topico test");
		ServletMock.addParameter("txConteudo", "junit topico test");
		CadastroTopicoServlet cadastroTopicoServlet = new CadastroTopicoServlet();
		cadastroTopicoServlet.service(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
		String mensagem = (String) ServletMock.getHttpServletRequest().getAttribute(WebUtil.ATRIBUTO_MENSAGEM);
		assertEquals(Constantes.MSG_FALHA_SALVAR_TOPICO, mensagem);
		ConfigUtil.definirConfiguracao("forumTest.properties");
	}
	
	@Test
	public void consultarTopicoSucesso() throws IOException, ServletException {
		List<Topico> topicos = topicoBusiness.recuperarTodos();
		assertNotNull(topicos);
		assertThat(topicos.size(),greaterThanOrEqualTo(1));		
		ServletMock.addParameter("txTopico", topicos.get(0).getId().toString());
		ConsultarTopicoServlet consultarTopicoServlet = new ConsultarTopicoServlet();
		consultarTopicoServlet.service(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
		Topico topico = (Topico) ServletMock.getHttpServletRequest().getAttribute("topico");
		assertNotNull(topico);
		assertEquals(topicos.get(0).getId(), topico.getId());
	}
	
	@Test
	public void consultarTopicoFalha() throws IOException, ServletException {
		ConfigUtil.definirConfiguracao(null);
		ServletMock.addParameter("txTopico", "1");
		ConsultarTopicoServlet consultarTopicoServlet = new ConsultarTopicoServlet();
		consultarTopicoServlet.service(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
		String mensagem = (String) ServletMock.getHttpServletRequest().getAttribute(WebUtil.ATRIBUTO_MENSAGEM);
		assertEquals(Constantes.MSG_FALHA_BUSCAR_TOPICO, mensagem);
		ConfigUtil.definirConfiguracao("forumTest.properties");
	}
	
	@Test
	public void listarTopicoSucesso() throws IOException, ServletException {
		ListarTopicoServlet listarTopicoServlet = new ListarTopicoServlet();
		listarTopicoServlet.service(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
		List<Topico> topicos = (List) ServletMock.getHttpServletRequest().getAttribute("topicos");
		assertNotNull(topicos);
		assertThat(topicos.size(),greaterThanOrEqualTo(1));
	}
	
	@Test
	public void listarTopicoFalha() throws IOException, ServletException {
		ConfigUtil.definirConfiguracao(null);
		ListarTopicoServlet listarTopicoServlet = new ListarTopicoServlet();
		listarTopicoServlet.service(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
		String mensagem = (String) ServletMock.getHttpServletRequest().getAttribute(WebUtil.ATRIBUTO_MENSAGEM);
		assertEquals(Constantes.MSG_FALHA_RECUPERAR_TOPICO, mensagem);
		ConfigUtil.definirConfiguracao("forumTest.properties");
	}
	
	@Test
	public void cadastroComentarioSucesso() throws IOException, ServletException {
		List<Topico> topicos = topicoBusiness.recuperarTodos();
		assertNotNull(topicos);
		assertThat(topicos.size(),greaterThanOrEqualTo(1));		
		ServletMock.addParameter("txTopico", topicos.get(0).getId().toString());
		ServletMock.addParameter("txComentario", "junit comentario test");
		CadastroComentarioServlet cadastroComentarioServlet = new CadastroComentarioServlet();
		cadastroComentarioServlet.service(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
		String mensagem = (String) ServletMock.getHttpServletRequest().getAttribute(WebUtil.ATRIBUTO_MENSAGEM);
		assertEquals(Constantes.MSG_SUCESSO_SALVAR_COMENTARIO, mensagem);
	}
	
	@Test
	public void cadastroComentarioFalha() throws IOException, ServletException {
		ServletMock.addParameter("txComentario", "junit comentario test");
		CadastroComentarioServlet cadastroComentarioServlet = new CadastroComentarioServlet();
		cadastroComentarioServlet.service(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
		String mensagem = (String) ServletMock.getHttpServletRequest().getAttribute(WebUtil.ATRIBUTO_MENSAGEM);
		assertEquals(Constantes.MSG_FALHA_SALVAR_COMENTARIO, mensagem);
	}
	
	@Test
	public void verificarUsuarioLogadoFilter() throws IOException, ServletException {
		UsuarioLogadoFilter usuarioLogadoFilter = new UsuarioLogadoFilter();
		usuarioLogadoFilter.init(null);
		usuarioLogadoFilter.doFilter(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse(), ServletMock.getFilterChainMock());
	}
	
	@Test
	public void validarContextListener() throws IOException, ServletException {
		ContextListener contextListener = new ContextListener();
		contextListener.contextDestroyed(null);
	}
	
	@Test
	public void webUtilRedirect() throws IOException, ServletException {
		WebUtil.redirecionarPaginaTopicoCadastro(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
		WebUtil.redirecionarServletRankingUsuario(ServletMock.getHttpServletRequest(), ServletMock.getHttpServletResponse());
	}
}
