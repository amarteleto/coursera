package br.com.marteleto.coursera.forum.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.marteleto.coursera.forum.dao.interfaces.IUsuarioDao;
import br.com.marteleto.coursera.forum.exception.DaoException;
import br.com.marteleto.coursera.forum.util.ConfigUtil;
import br.com.marteleto.coursera.forum.util.Constantes;
import br.com.marteleto.coursera.forum.vo.Usuario;

public class UsuarioDao implements IUsuarioDao {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UsuarioDao.class.getName());
	
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
		sql.append(" SELECT ");
		sql.append(" 	usua.*");
		sql.append(" FROM");
		sql.append(" 	usuario usua");
		sql.append(" WHERE 1=1");
		sql.append(" 	AND usua.login = ?");
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
				Usuario usuario = new Usuario();
				usuario.setLogin(resultSet.getString("login"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setPontos(resultSet.getInt("pontos"));
				return usuario;
			}
			return null;
		} catch (SQLException ex) {
			throw new DaoException(Constantes.MSG_FALHA_RECUPERAR_USUARIO,ex);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					log.log(Level.SEVERE, ex.getMessage(), ex);
				}
			}
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
		sql.append(" 	usua.login,");
		sql.append(" 	usua.pontos,");
		sql.append(" 	usua.nome");
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
				Usuario usuario = new Usuario();
				usuario.setLogin(resultSet.getString("login"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setPontos(resultSet.getInt("pontos"));
				usuarios.add(usuario);
			}
			return usuarios;
		} catch (SQLException ex) {
			throw new DaoException(Constantes.MSG_FALHA_RECUPERAR_RANKING_USUARIO,ex);
		}
	}
}
