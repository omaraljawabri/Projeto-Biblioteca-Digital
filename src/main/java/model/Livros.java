package model;

import java.awt.image.BufferedImage;

public class Livros {
	private String idcon;
	private String titulo;
	private String autor;
	private String anoDePublicacao;
	private String categoria;
	private String local;
	private String imagemDeCapa;
	private String estaEmprestado;
	private String idLocador;
	private String nomeLocador;

	public Livros() {
		super();
	}



	public Livros(String idcon, String titulo, String autor, String anoDePublicacao, String categoria, String local,
			String imagemDeCapa, String estaEmprestado, String idLocador, String nomeLocador) {
		super();
		this.idcon = idcon;
		this.titulo = titulo;
		this.autor = autor;
		this.anoDePublicacao = anoDePublicacao;
		this.categoria = categoria;
		this.local = local;
		this.imagemDeCapa = imagemDeCapa;
		this.estaEmprestado = estaEmprestado;
		this.idLocador = idLocador;
		this.nomeLocador = nomeLocador;
	}
	
	public Livros(String idcon, String titulo, String autor, String anoDePublicacao, String imagemDeCapa) {
		super();
		this.idcon = idcon;
		this.titulo = titulo;
		this.autor = autor;
		this.anoDePublicacao = anoDePublicacao;
		this.imagemDeCapa = imagemDeCapa;
	}



	public String getIdcon() {
		return idcon;
	}

	public void setIdcon(String idcon) {
		this.idcon = idcon;
	}

	public String getEstaEmprestado() {
		return estaEmprestado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAnoDePublicacao() {
		return anoDePublicacao;
	}

	public void setAnoDePublicacao(String anoDePublicacao) {
		this.anoDePublicacao = anoDePublicacao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getImagemDeCapa() {
		return imagemDeCapa;
	}

	public void setImagemDeCapa(String imagemDeCapa) {
		this.imagemDeCapa = imagemDeCapa;
	}

	public String setEstaEmprestado() {
		return estaEmprestado;
	}

	public void setEstaEmprestado(String estaEmprestado) {
		this.estaEmprestado = estaEmprestado;
	}

	public String getIdLocador() {
		return idLocador;
	}

	public void setIdLocador(String idLocador) {
		this.idLocador = idLocador;
	}



	public String getNomeLocador() {
		return nomeLocador;
	}



	public void setNomeLocador(String nomeLocador) {
		this.nomeLocador = nomeLocador;
	}
	
	
	
}
