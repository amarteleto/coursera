package br.com.marteleto.coursera.forum.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.marteleto.coursera.forum.dao.interfaces.ITopicoDao;
import br.com.marteleto.coursera.forum.util.ConfigUtil;
import br.com.marteleto.coursera.forum.util.Constantes;
import br.com.marteleto.coursera.forum.vo.Topico;
import br.com.marteleto.coursera.forum.vo.Usuario;

public class TopicoDao implements ITopicoDao {
	private static final long serialVersionUID = 1L;

	@Override
	public List<Topico> recuperarTodos() {
		try (
			Connection connection = DriverManager.getConnection(ConfigUtil.getDatabaseUrl());
		){
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT");
			sql.append(" 	topi.id_topico,");
			sql.append(" 	topi.titulo,");
			sql.append(" 	usua.login,");
			sql.append(" 	usua.nome");
			sql.append(" FROM");
			sql.append(" 	topico topi");
			sql.append(" 	INNER JOIN usuario usua on usua.login = topi.login");
			sql.append(" ORDER BY");
			sql.append(" 	topi.id_topico");
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Topico> topicos = new ArrayList<>();
			while (resultSet.next()) {
				Usuario criador = new Usuario();
				criador.setLogin(resultSet.getString("login"));
				criador.setNome(resultSet.getString("nome"));
				Topico topico = new Topico();
				topico.setId(resultSet.getInt("id_topico"));
				topico.setTitulo(resultSet.getString("titulo"));
				topico.setCriador(criador);
				topicos.add(topico);
			}
			return topicos;
		} catch (SQLException ex) {
			throw new RuntimeException(Constantes.MSG_FALHA_RECUPERAR_TOPICO,ex);
		}
	}

	@Override
	public Topico buscarTopico(Integer id) {
		try (
			Connection connection = DriverManager.getConnection(ConfigUtil.getDatabaseUrl());
		){
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT");
			sql.append(" 	topi.id_topico,");
			sql.append(" 	topi.titulo,");
			sql.append(" 	usua.login,");
			sql.append(" 	usua.nome");
			sql.append(" FROM");
			sql.append(" 	topico topi");
			sql.append(" 	INNER JOIN usuario usua on usua.login = topi.login");
			sql.append(" WHERE");
			sql.append(" 	topi.id_topico = ?");
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Usuario criador = new Usuario();
				criador.setLogin(resultSet.getString("login"));
				criador.setNome(resultSet.getString("nome"));
				Topico topico = new Topico();
				topico.setId(resultSet.getInt("id_topico"));
				topico.setTitulo(resultSet.getString("titulo"));
				topico.setCriador(criador);
				return topico;
			}
			return null;
		} catch (SQLException ex) {
			throw new RuntimeException(Constantes.MSG_FALHA_BUSCAR_TOPICO,ex);
		}
	}
	
	@Override
	public void salvar(Topico topico) {
		try (
			Connection connection = DriverManager.getConnection(ConfigUtil.getDatabaseUrl());
		){
			String sql = "INSERT INTO topico(titulo, conteudo, login) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, topico.getTitulo());
			preparedStatement.setString(2, topico.getConteudo());
			preparedStatement.setString(3, topico.getCriador().getLogin());
			preparedStatement.execute();
		} catch (SQLException ex) {
			throw new RuntimeException(Constantes.MSG_FALHA_SALVAR_TOPICO,ex);
		}
	}

}
