package br.com.marteleto.coursera.forum.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.marteleto.coursera.forum.dao.interfaces.IComentarioDao;
import br.com.marteleto.coursera.forum.util.ConfigUtil;
import br.com.marteleto.coursera.forum.util.Constantes;
import br.com.marteleto.coursera.forum.vo.Comentario;
import br.com.marteleto.coursera.forum.vo.Topico;
import br.com.marteleto.coursera.forum.vo.Usuario;

public class ComentarioDao implements IComentarioDao {
	private static final long serialVersionUID = 1L;

	@Override
	public List<Comentario> recuperarTodos(Integer topico) {
		try (
			Connection connection = DriverManager.getConnection(ConfigUtil.getDatabaseUrl());
		){
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
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setInt(1, topico);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Comentario> comentarios = new ArrayList<>();
			while (resultSet.next()) {
				Comentario comentario = new Comentario();
				comentario.setId(resultSet.getInt("id_comentario"));
				comentario.setComentario(resultSet.getString("comentario"));
				comentario.setCriador(new Usuario());
				comentario.getCriador().setLogin(resultSet.getString("login"));
				comentario.getCriador().setNome(resultSet.getString("nome"));
				comentario.setTopico(new Topico());
				comentario.getTopico().setId(resultSet.getInt("id_topico"));
				comentario.getTopico().setTitulo(resultSet.getString("titulo"));
				comentarios.add(comentario);
			}
			return comentarios;
		} catch (SQLException ex) {
			throw new RuntimeException(Constantes.MSG_FALHA_RECUPERAR_COMENTARIO,ex);
		}
	}

	@Override
	public void salvar(Comentario comentario) {
		try (
				Connection connection = DriverManager.getConnection(ConfigUtil.getDatabaseUrl());
			){
				String sql = "INSERT INTO comentario(comentario, login, id_topico) VALUES (?, ?, ?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, comentario.getComentario());
				preparedStatement.setString(2, comentario.getCriador().getLogin());
				preparedStatement.setInt(3, comentario.getTopico().getId());
				preparedStatement.execute();
			} catch (SQLException ex) {
				throw new RuntimeException(Constantes.MSG_FALHA_SALVAR_COMENTARIO,ex);
			}
	}
}
