package br.com.marteleto.coursera.forum.util;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.stream.Stream;

public class ConfigUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	private static boolean ignoreInserts = false;
	private static Properties properties = new Properties();
	private static String create = ";create=true";
	
	public static void definirConfiguracao(String file) {
		ConfigUtil.definirConfiguracao(file,false);
	}

	public static void definirConfiguracao(String file, boolean ignoreInserts) {
		try {
			ConfigUtil.ignoreInserts = ignoreInserts;
			ConfigUtil.properties.load(ConfigUtil.class.getClassLoader().getResourceAsStream(file));
			ConfigUtil.criarBancoDeDados();
		} catch (IOException ex) {
			throw new RuntimeException("Falha ao carregar configuração.", ex);
		}
	}
	
	public static String getDatabaseClass() {
		if (!ConfigUtil.properties.isEmpty()) {
			return ConfigUtil.properties.getProperty("database.class");
		}
		throw new RuntimeException("Configuração não encontrada.");
	}
	
	public static String getDatabaseUrl() {
		if (!ConfigUtil.properties.isEmpty()) {
			return ConfigUtil.properties.getProperty("database.url");
		}
		throw new RuntimeException("Configuração não encontrada.");
	}
	
	private static void criarBancoDeDados() {
		try (
				Stream<String> stream = Files.lines(Paths.get(ConfigUtil.class.getClassLoader().getResource("database.sql").toURI()));
		) {
			Class.forName(ConfigUtil.getDatabaseClass());
			stream.forEach(ConfigUtil::criarTabela);
		} catch (Exception ex) {
			throw new RuntimeException("Falha ao carregar banco de dados.",ex);
		}
	}
	
	private static void criarTabela(String sql) {
		try (
				Connection connection = DriverManager.getConnection(ConfigUtil.getDatabaseUrl() + ConfigUtil.create);
			){
				if (sql.startsWith("--") || (ConfigUtil.ignoreInserts && sql.toLowerCase().startsWith("insert"))) {
					//NAO FAZ NADA
				} else {
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.execute();
				}
				ConfigUtil.create = "";
			} catch (SQLException ex) {
				if(ex.getErrorCode() != 30000) {
					throw new RuntimeException("Não foi possível criar as tabelas do banco de dados.",ex);
			    }
			}
	}
}
