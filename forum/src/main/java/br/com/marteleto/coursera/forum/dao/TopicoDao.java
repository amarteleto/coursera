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

import br.com.marteleto.coursera.forum.dao.interfaces.ITopicoDao;
import br.com.marteleto.coursera.forum.exception.DaoException;
import br.com.marteleto.coursera.forum.util.ConfigUtil;
import br.com.marteleto.coursera.forum.util.Constantes;
import br.com.marteleto.coursera.forum.vo.Topico;
import br.com.marteleto.coursera.forum.vo.Usuario;

public class TopicoDao implements ITopicoDao {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(TopicoDao.class.getName());
	
	@Override
	public List<Topico> recuperarTodos() {
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
		try (
			Connection connection = DriverManager.getConnection(ConfigUtil.getDatabaseUrl());
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
		){
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
			throw new DaoException(Constantes.MSG_FALHA_RECUPERAR_TOPICO,ex);
		}
	}

	@Override
	public Topico buscarTopico(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT");
		sql.append(" 	topi.id_topico,");
		sql.append(" 	topi.titulo,");
		sql.append(" 	topi.conteudo,");
		sql.append(" 	usua.login,");
		sql.append(" 	usua.nome");
		sql.append(" FROM");
		sql.append(" 	topico topi");
		sql.append(" 	INNER JOIN usuario usua on usua.login = topi.login");
		sql.append(" WHERE");
		sql.append(" 	topi.id_topico = ?");
		ResultSet resultSet = null;
		try (
			Connection connection = DriverManager.getConnection(ConfigUtil.getDatabaseUrl());
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		){
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			Topico topico = null;
			if (resultSet.next()) {
				Usuario criador = new Usuario();
				criador.setLogin(resultSet.getString("login"));
				criador.setNome(resultSet.getString("nome"));
				topico = new Topico();
				topico.setId(resultSet.getInt("id_topico"));
				topico.setTitulo(resultSet.getString("titulo"));
				topico.setConteudo(resultSet.getString("conteudo"));
				topico.setCriador(criador);
			}
			return topico;
		} catch (SQLException ex) {
			throw new DaoException(Constantes.MSG_FALHA_BUSCAR_TOPICO,ex);
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
	public void salvar(Topico topico) {
		String sql = "INSERT INTO topico(titulo, conteudo, login) VALUES (?, ?, ?)";
		try (
			Connection connection = DriverManager.getConnection(ConfigUtil.getDatabaseUrl());
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
		){
			preparedStatement.setString(1, topico.getTitulo());
			preparedStatement.setString(2, topico.getConteudo());
			preparedStatement.setString(3, topico.getCriador().getLogin());
			preparedStatement.execute();
		} catch (SQLException ex) {
			throw new DaoException(Constantes.MSG_FALHA_SALVAR_TOPICO,ex);
		}
	}

}
