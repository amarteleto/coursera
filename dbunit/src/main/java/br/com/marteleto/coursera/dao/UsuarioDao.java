package br.com.marteleto.coursera.dao;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import br.com.marteleto.coursera.dao.interfaces.IUsuarioDao;
import br.com.marteleto.coursera.vo.Usuario;

public class UsuarioDao implements IUsuarioDao {
	private static final long serialVersionUID = 1L;
	private final String databaseClass;
	private final String databaseUrl;
	
	public UsuarioDao(String databaseClass,String databaseUrl) {
		this.databaseUrl = databaseUrl;
		this.databaseClass = databaseClass;
		try (
				Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource("database.sql").toURI()));
		) {
			Class.forName(this.databaseClass);
			stream.forEach(this::criarEstrutura);
		} catch (Exception ex) {
			throw new RuntimeException("Falha ao carregar banco de dados.");
		}
	}
	
	private void criarEstrutura(String sql) {
		try (
				Connection connection = DriverManager.getConnection(this.databaseUrl + ";create=true");
			){
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.execute();
			} catch (SQLException ex) {
				throw new RuntimeException("Não foi possível inserir o usuário.");
			}
	}
	
	@Override
	public void inserir(Usuario u) {
		try (
			Connection connection = DriverManager.getConnection(this.databaseUrl);
		){
			String sql = "INSERT INTO usuario(login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, u.getLogin());
			preparedStatement.setString(2, u.getEmail());
			preparedStatement.setString(3, u.getNome());
			preparedStatement.setString(4, u.getSenha());
			preparedStatement.setInt(5, u.getPontos());
			preparedStatement.execute();
		} catch (SQLException ex) {
			throw new RuntimeException("Não foi possível inserir o usuário.");
		}
	}

	@Override
	public Usuario recuperar(String login) {
		try (
			Connection connection = DriverManager.getConnection(this.databaseUrl);
		){
			String sql = "SELECT * FROM usuario WHERE login = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setLogin(resultSet.getString("login"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setPontos(resultSet.getInt("pontos"));
				return usuario;
			}
			return null;
		} catch (SQLException ex) {
			throw new RuntimeException("Falha ao recuperar usuário: " + login);
		}
	}

	@Override
	public void adicionarPontos(String login, int pontos) {
		Usuario usuario = this.recuperar(login);
		if (usuario != null) {
			Integer pontosBd = usuario.getPontos();
			if (pontosBd == null) {
				pontosBd = 0;
			}
			pontosBd = pontosBd + pontos;
			try (
				Connection connection = DriverManager.getConnection(this.databaseUrl);
			){
				String sql = "UPDATE usuario SET pontos = ? WHERE login = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, pontosBd);
				preparedStatement.setString(2, login);
				preparedStatement.execute();
			} catch (SQLException ex) {
				throw new RuntimeException("Falha ao adicionar pontos ao usuário: " + login);
			}
		} else {
			throw new RuntimeException("Falha ao adicionar pontos ao usuário: " + login);
		}
	}

	@Override
	public List<Usuario> ranking() {
		List<Usuario> usuarios = new ArrayList<>();
		try (
			Connection connection = DriverManager.getConnection(this.databaseUrl);
		){
			String sql = "SELECT * FROM usuario ORDER BY pontos DESC";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setLogin(resultSet.getString("login"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setPontos(resultSet.getInt("pontos"));
				usuarios.add(usuario);
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Falha ao executar ranking de usuários.");
		}
		return usuarios;
	}

}
