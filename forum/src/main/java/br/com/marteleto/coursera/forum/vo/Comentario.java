package br.com.marteleto.coursera.forum.vo;

import java.io.Serializable;

public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String conteudo;
	private Usuario criador;
	private Topico topico;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public Usuario getCriador() {
		return criador;
	}
	public void setCriador(Usuario criador) {
		this.criador = criador;
	}
	public Topico getTopico() {
		return topico;
	}
	public void setTopico(Topico topico) {
		this.topico = topico;
	}
}
