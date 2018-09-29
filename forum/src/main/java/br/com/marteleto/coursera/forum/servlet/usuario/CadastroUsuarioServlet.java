package br.com.marteleto.coursera.forum.servlet.usuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.marteleto.coursera.forum.business.UsuarioBusiness;
import br.com.marteleto.coursera.forum.business.interfaces.IUsuarioBusiness;
import br.com.marteleto.coursera.forum.util.Constantes;
import br.com.marteleto.coursera.forum.util.WebUtil;
import br.com.marteleto.coursera.forum.vo.Usuario;

@WebServlet(value="/usuario/cadastro.slt")
public class CadastroUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final IUsuarioBusiness usuarioBusiness = new UsuarioBusiness();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String login = req.getParameter("txLogin");
			String email = req.getParameter("txEmail");
			String senha = req.getParameter("txSenha");
			String nome = req.getParameter("txNome");
			Usuario usuario = new Usuario();
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setEmail(email);
			usuario.setNome(nome);
			usuarioBusiness.salvar(usuario);
			WebUtil.publicarMensagemSucesso(req, Constantes.MSG_SUCESSO_SALVAR_USUARIO);
			WebUtil.redirecionarPaginaLogin(req, resp);
		} catch (Exception ex) {
			WebUtil.publicarMensagemFalha(req,Constantes.MSG_FALHA_SALVAR_USUARIO, ex);
			WebUtil.redirecionarPaginaUsuarioCadastro(req, resp);
		}
	}
}
