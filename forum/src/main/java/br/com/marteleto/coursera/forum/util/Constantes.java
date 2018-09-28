package br.com.marteleto.coursera.forum.util;

import java.io.Serializable;

public class Constantes implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static Integer PONTOS_TOPICO = 10;
	public static Integer PONTOS_COMENTARIO = 3;

	public static String USUARIO_LOGADO_PARAMETER = "usuarioLogado";
	
	public static String MSG_SUCESSO_AUTENTICACAO = "Seja bem vindo, usuário!";
	public static String MSG_SUCESSO_SALVAR_USUARIO = "Usuário cadastrado com sucesso!";
	public static String MSG_SUCESSO_SALVAR_TOPICO = "Tópico cadastrado com sucesso!";
	public static String MSG_SUCESSO_SALVAR_COMENTARIO = "Comentário cadastrado com sucesso!";
	
	public static String MSG_FALHA_AUTENTICACAO = "Usuário não encontrado.";
	public static String MSG_FALHA_SALVAR_USUARIO = "Falha ao salvar usuário.";
	public static String MSG_FALHA_SALVAR_TOPICO = "Falha ao salvar tópico.";
	public static String MSG_FALHA_SALVAR_COMENTARIO = "Falha ao salvar comentário.";
	public static String MSG_FALHA_ADICIONAR_PONTO_USUARIO = "Falha ao adcionar ponto ao usuário.";
	public static String MSG_FALHA_RECUPERAR_USUARIO = "Falha ao recuperar ao usuário.";
	public static String MSG_FALHA_RECUPERAR_RANKING_USUARIO = "Falha ao recuperar ranking de usuários.";
	public static String MSG_FALHA_RECUPERAR_TOPICO = "Falha ao recuperar ao tópicos.";
	public static String MSG_FALHA_RECUPERAR_COMENTARIO = "Falha ao recuperar ao comentários.";
	public static String MSG_FALHA_BUSCAR_TOPICO = "Falha ao buscar ao tópics.";
	
}