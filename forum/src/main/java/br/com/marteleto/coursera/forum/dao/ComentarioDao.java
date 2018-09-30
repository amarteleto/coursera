package br.com.marteleto.coursera.forum.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.marteleto.coursera.forum.dao.interfaces.IComentarioDao;
import br.com.marteleto.coursera.forum.exception.DaoException;
import br.com.marteleto.coursera.forum.util.ConfigUtil;
import br.com.marteleto.coursera.forum.util.Constantes;
import br.com.marteleto.coursera.forum.vo.Comentario;
import br.com.marteleto.coursera.forum.vo.Topico;
import br.com.marteleto.coursera.forum.vo.Usuario;

public class ComentarioDao extends ADao implements IComentarioDao {
	private static final long serialVersionUID = 1L;
	
	private String getSqlRecuperarTodos() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT");
		sql.append(" 	come.id_comentario,");
		sql.append(" 	come.comentario,");
		sql.append(" 	topi.id_topico,");
		sql.append(" 	topi.titulo,");
		sql.append(" 	usua.login,");
		sql.append(" 	usua.nome");
		sql.append(" FROM");
		sql.append(" 	comentario come");
		sql.append(" 	INNER JOIN topico topi ON topi.id_topico = come.id_topico");
		sql.append(" 	INNER JOIN usuario usua ON usua.login = come.login");
		sql.append(" WHERE 1 = 1");
		sql.append(" 	AND topi.id_topico = ?");
		sql.append(" ORDER BY");
		sql.append(" 	come.id_comentario");
		return sql.toString();
	}
	
	private Comentario criarComentario(ResultSet resultSet) throws SQLException {
		Comentario comentario = new Comentario();
		comentario.setId(resultSet.getInt("id_comentario"));
		comentario.setConteudo(resultSet.getString("comentario"));
		comentario.setCriador(new Usuario());
		comentario.getCriador().setLogin(resultSet.getString("login"));
		comentario.getCriador().setNome(resultSet.getString("nome"));
		comentario.setTopico(new Topico());
		comentario.getTopico().setId(resultSet.getInt("id_topico"));
		comentario.getTopico().setTitulo(resultSet.getString("titulo"));
		return comentario;
	}

	@Override
	public List<Comentario> recuperarTodos(Integer topico) {
		ResultSet resultSet = null;
		try (
			Connection connection = DriverManager.getConnection(ConfigUtil.getDatabaseUrl());
			PreparedStatement preparedStatement = connection.prepareStatement(this.getSqlRecuperarTodos());
		){
			preparedStatement.setInt(1, topico);
			resultSet = preparedStatement.executeQuery();
			List<Comentario> comentarios = new ArrayList<>();
			while (resultSet.next()) {
				comentarios.add(this.criarComentario(resultSet));
			}
			return comentarios;
		} catch (SQLException ex) {
			throw new DaoException(Constantes.MSG_FALHA_RECUPERAR_COMENTARIO,ex);
		} finally {
			closeResultSet(resultSet);
		}
	}

	@Override
	public void salvar(Comentario comentario) {
		String sql = "INSERT INTO comentario(comentario, login, id_topico) VALUES (?, ?, ?)";
		try (
				Connection connection = DriverManager.getConnection(ConfigUtil.getDatabaseUrl());
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
			){
				preparedStatement.setString(1, comentario.getConteudo());
				preparedStatement.setString(2, comentario.getCriador().getLogin());
				preparedStatement.setInt(3, comentario.getTopico().getId());
				preparedStatement.execute();
			} catch (SQLException ex) {
				throw new DaoException(Constantes.MSG_FALHA_SALVAR_COMENTARIO,ex);
			}
	}
}
