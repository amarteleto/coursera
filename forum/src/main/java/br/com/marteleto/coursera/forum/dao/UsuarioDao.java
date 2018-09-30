package br.com.marteleto.coursera.forum.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.marteleto.coursera.forum.dao.interfaces.IUsuarioDao;
import br.com.marteleto.coursera.forum.exception.DaoException;
import br.com.marteleto.coursera.forum.util.ConfigUtil;
import br.com.marteleto.coursera.forum.util.Constantes;
import br.com.marteleto.coursera.forum.vo.Usuario;

public class UsuarioDao extends ADao implements IUsuarioDao {
	private static final long serialVersionUID = 1L;
	
	private Usuario criarUsuario(ResultSet resultSet) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setLogin(resultSet.getString("login"));
		usuario.setEmail(resultSet.getString("email"));
		usuario.setSenha(resultSet.getString("senha"));
		usuario.setNome(resultSet.getString("nome"));
		usuario.setPontos(resultSet.getInt("pontos"));
		return usuario;
	}

	@Override
	public void salvar(Usuario usuario) {
		String sql = "INSERT INTO usuario(login, email, senha, nome, pontos) VALUES (?, ?, ?, ?, ?)";
		try (
			Connection connection = DriverManager.getConnection(ConfigUtil.getDatabaseUrl());
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
		){
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setString(3, usuario.getSenha());
			preparedStatement.setString(4, usuario.getNome());
			preparedStatement.setInt(5, 0);
			preparedStatement.execute();
		} catch (SQLException ex) {
			throw new DaoException(Constantes.MSG_FALHA_SALVAR_USUARIO,ex);
		}
	}

	@Override
	public Usuario recuperar(String login, String senha) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT usua.*  FROM usuario usua WHERE 1=1 AND usua.login = ?");
		if (senha != null) {
			sql.append(" 	AND usua.senha = ?");
		}
		ResultSet resultSet = null;
		try (
			Connection connection = DriverManager.getConnection(ConfigUtil.getDatabaseUrl());
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		){
			preparedStatement.setString(1, login);
			if (senha != null) {
				preparedStatement.setString(2, senha);
			}
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return this.criarUsuario(resultSet);
			}
			return null;
		} catch (SQLException ex) {
			throw new DaoException(Constantes.MSG_FALHA_RECUPERAR_USUARIO,ex);
		} finally {
			closeResultSet(resultSet);
		}
	}

	@Override
	public void adicionarPontos(String login, Integer pontos) {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE usuario SET");
		sql.append(" 	pontos = (pontos + ?)");
		sql.append(" WHERE 1=1");
		sql.append(" 	AND login = ?");
		try (
				Connection connection = DriverManager.getConnection(ConfigUtil.getDatabaseUrl());
				PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			){
				preparedStatement.setInt(1, pontos);
				preparedStatement.setString(2, login);
				preparedStatement.execute();
			} catch (SQLException ex) {
				throw new DaoException(Constantes.MSG_FALHA_ADICIONAR_PONTO_USUARIO,ex);
			}
	}

	@Override
	public List<Usuario> recuperarRanking() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT");
		sql.append(" 	usua.* ");
		sql.append(" FROM");
		sql.append(" 	usuario usua");
		sql.append(" ORDER BY");
		sql.append(" 	usua.pontos DESC");
		try (
			Connection connection = DriverManager.getConnection(ConfigUtil.getDatabaseUrl());
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
		){
			List<Usuario> usuarios = new ArrayList<>();
			while (resultSet.next()) {
				usuarios.add(this.criarUsuario(resultSet));
			}
			return usuarios;
		} catch (SQLException ex) {
			throw new DaoException(Constantes.MSG_FALHA_RECUPERAR_RANKING_USUARIO,ex);
		}
	}
}
