package br.com.marteleto.coursera.dbunit.dao;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import br.com.marteleto.coursera.dbunit.dao.interfaces.IUsuarioDao;
import br.com.marteleto.coursera.dbunit.exception.DaoException;
import br.com.marteleto.coursera.dbunit.vo.Usuario;

public class UsuarioDao implements IUsuarioDao {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UsuarioDao.class.getName());
	private final String databaseClass;
	private final String databaseUrl;
	
	private Usuario criarUsuario(ResultSet resultSet) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setLogin(resultSet.getString("login"));
		usuario.setEmail(resultSet.getString("email"));
		usuario.setNome(resultSet.getString("nome"));
		usuario.setSenha(resultSet.getString("senha"));
		usuario.setPontos(resultSet.getInt("pontos"));
		return usuario;
	}
	
	public UsuarioDao(String databaseClass,String databaseUrl) {
		this.databaseUrl = databaseUrl;
		this.databaseClass = databaseClass;
		try (
				Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource("database.sql").toURI()));
		) {
			Class.forName(this.databaseClass);
			stream.forEach(this::criarEstrutura);
		} catch (Exception ex) {
			throw new DaoException("Falha ao carregar banco de dados.",ex);
		}
	}
	
	private void criarEstrutura(String sql) {
		try (
				Connection connection = DriverManager.getConnection(this.databaseUrl + ";create=true");
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
			){
				preparedStatement.execute();
			} catch (SQLException ex) {
				throw new DaoException("Não foi possível inserir o usuário.",ex);
			}
	}
	
	@Override
	public void inserir(Usuario u) {
		String sql = "INSERT INTO usuario(login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?)";
		try (
			Connection connection = DriverManager.getConnection(this.databaseUrl);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
		){
			
			preparedStatement.setString(1, u.getLogin());
			preparedStatement.setString(2, u.getEmail());
			preparedStatement.setString(3, u.getNome());
			preparedStatement.setString(4, u.getSenha());
			preparedStatement.setInt(5, u.getPontos());
			preparedStatement.execute();
		} catch (SQLException ex) {
			throw new DaoException("Não foi possível inserir o usuário.",ex);
		}
	}

	@Override
	public Usuario recuperar(String login) {
		String sql = "SELECT * FROM usuario WHERE login = ?";
		ResultSet resultSet = null;
		try (
			Connection connection = DriverManager.getConnection(this.databaseUrl);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
		){
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return this.criarUsuario(resultSet);
			}
			return null;
		} catch (SQLException ex) {
			throw new DaoException("Falha ao recuperar usuário: " + login,ex);
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
	public void adicionarPontos(String login, int pontos) {
		Usuario usuario = this.recuperar(login);
		if (usuario != null) {
			Integer pontosBd = usuario.getPontos() + pontos;
			String sql = "UPDATE usuario SET pontos = ? WHERE login = ?";
			try (
				Connection connection = DriverManager.getConnection(this.databaseUrl);
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
			){
				preparedStatement.setInt(1, pontosBd);
				preparedStatement.setString(2, login);
				preparedStatement.execute();
			} catch (SQLException ex) {
				throw new DaoException("Falha ao adicionar pontos ao usuário: " + login,ex);
			}
		} else {
			throw new DaoException("Falha ao adicionar pontos ao usuário: " + login);
		}
	}

	@Override
	public List<Usuario> ranking() {
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM usuario ORDER BY pontos DESC";
		try (
			Connection connection = DriverManager.getConnection(this.databaseUrl);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
		){
			while (resultSet.next()) {
				usuarios.add(this.criarUsuario(resultSet));
			}
		} catch (SQLException ex) {
			throw new DaoException("Falha ao executar ranking de usuários.",ex);
		}
		return usuarios;
	}

}
