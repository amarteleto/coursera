package br.com.marteleto.coursera.forum.util;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.marteleto.coursera.forum.exception.BusinessException;

public class ConfigUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ConfigUtil.class.getName());
	private static boolean ignoreInserts = false;
	private static Properties properties = new Properties();
	private static String create = ";create=true";
	private static final String MENSAGEM_PADRAO = "Configuração nao encontrada.";
	
	public static void definirConfiguracao(String file) {
		ConfigUtil.properties.clear();
		try {
			ConfigUtil.properties.load(ConfigUtil.class.getClassLoader().getResourceAsStream(file));
		} catch (Exception ex) {
			log.log(Level.WARNING, "Falha ao carregar configuração.", ex);
		}
	}

	public static void prepararBancoDeDados(boolean ignoreInserts) {
		ConfigUtil.ignoreInserts = ignoreInserts;
		ConfigUtil.criarBancoDeDados();
	}

	
	public static String getDatabaseClass() {
		if (!ConfigUtil.properties.isEmpty()) {
			return ConfigUtil.properties.getProperty("database.class");
		}
		throw new BusinessException(MENSAGEM_PADRAO);
	}
	
	public static String getDatabaseUrl() {
		if (!ConfigUtil.properties.isEmpty()) {
			return ConfigUtil.properties.getProperty("database.url");
		}
		throw new BusinessException(MENSAGEM_PADRAO);
	}

    public static String getSeleniumUrl() {
   		if (!ConfigUtil.properties.isEmpty()) {
			return ConfigUtil.properties.getProperty("selenium.url");
		}
		throw new BusinessException(MENSAGEM_PADRAO);
    }
	
	private static void criarBancoDeDados() {
		try (
				Scanner scanner = new Scanner(new File(ConfigUtil.class.getClassLoader().getResource("database.sql").getFile()))
		) {
			Class.forName(ConfigUtil.getDatabaseClass());
			while (scanner.hasNext()){
				criarTabela(scanner.nextLine());
			}
		} catch (Exception ex) {
			throw new BusinessException("Falha ao carregar banco de dados.",ex);
		}
	}
	
	private static boolean executarSql(String sql) {
		return !(sql.startsWith("--") || (ConfigUtil.ignoreInserts && sql.toLowerCase().startsWith("insert")));
	}
	
	private static void validarSqlException(SQLException ex) {
		if(ex.getErrorCode() != 30000) {
			throw new BusinessException("Não foi possível criar as tabelas do banco de dados.",ex);
	    }
	}
	
	private static void criarTabela(String sql) {
		PreparedStatement preparedStatement = null;
		try (
				Connection connection = DriverManager.getConnection(ConfigUtil.getDatabaseUrl() + ConfigUtil.create);
			){
				if (executarSql(sql)) {
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.execute();
				}
				ConfigUtil.create = "";
			} catch (SQLException ex) {
				validarSqlException(ex);
			} finally {
				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						//NAO FAZ NADA
					}
				}
			}
	}
}
