package model;

public class Usuario {
	private String idcon;
	private String nome;
	private String email;
	private String senha;
	private String tipoDeConta;
	
	public Usuario() {
		super();
	}
	
	public Usuario(String idcon, String nome, String email, String senha, String tipoDeConta) {
		super();
		this.idcon = idcon;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipoDeConta = tipoDeConta;
	}
	
	public Usuario(String email) {
		super();
		this.email = email;
	}

	public String getIdcon() {
		return idcon;
	}

	public void setIdcon(String idcon) {
		this.idcon = idcon;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipoDeConta() {
		return tipoDeConta;
	}

	public void setTipoDeConta(String tipoDeConta) {
		this.tipoDeConta = tipoDeConta;
	}
}
