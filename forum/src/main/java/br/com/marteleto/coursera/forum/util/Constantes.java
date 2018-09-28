package br.com.marteleto.coursera.forum.util;

import java.io.Serializable;

public class Constantes implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static Integer PONTOS_TOPICO = 10;
	public static Integer PONTOS_COMENTARIO = 3;

	public static String USUARIO_LOGADO_PARAMETER = "usuarioLogado";
	
	public static String MSG_SUCESSO_AUTENTICACAO = "Seja bem vindo, usu�rio!";
	public static String MSG_SUCESSO_SALVAR_USUARIO = "Usu�rio cadastrado com sucesso!";
	public static String MSG_SUCESSO_SALVAR_TOPICO = "T�pico cadastrado com sucesso!";
	public static String MSG_SUCESSO_SALVAR_COMENTARIO = "Coment�rio cadastrado com sucesso!";
	
	public static String MSG_FALHA_AUTENTICACAO = "Usu�rio n�o encontrado.";
	public static String MSG_FALHA_SALVAR_USUARIO = "Falha ao salvar usu�rio.";
	public static String MSG_FALHA_SALVAR_TOPICO = "Falha ao salvar t�pico.";
	public static String MSG_FALHA_SALVAR_COMENTARIO = "Falha ao salvar coment�rio.";
	public static String MSG_FALHA_ADICIONAR_PONTO_USUARIO = "Falha ao adcionar ponto ao usu�rio.";
	public static String MSG_FALHA_RECUPERAR_USUARIO = "Falha ao recuperar ao usu�rio.";
	public static String MSG_FALHA_RECUPERAR_RANKING_USUARIO = "Falha ao recuperar ranking de usu�rios.";
	public static String MSG_FALHA_RECUPERAR_TOPICO = "Falha ao recuperar ao t�picos.";
	public static String MSG_FALHA_RECUPERAR_COMENTARIO = "Falha ao recuperar ao coment�rios.";
	public static String MSG_FALHA_BUSCAR_TOPICO = "Falha ao buscar ao t�pics.";
	
}