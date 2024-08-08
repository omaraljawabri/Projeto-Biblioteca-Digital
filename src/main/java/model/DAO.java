package model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

import java.sql.Blob;

public class DAO {
	private String driver = "com.mysql.cj.jdbc.Driver";

	public Connection conectar() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con = null;
		Statement stm = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/", "root", "rootOmar");
			stm = con.createStatement();
			stm.executeUpdate("CREATE DATABASE IF NOT EXISTS bilbioteca");
			String usarDB = "USE biblioteca";
			String criarTabelaUsuario = "CREATE TABLE IF NOT EXISTS usuario(idcon int primary key auto_increment, nome varchar(50) not null, email varchar(50) not null, senha varchar(50) not null, tipodeconta varchar(30) not null);";
			String criarTabelaLivros = "CREATE TABLE IF NOT EXISTS livros(idcon int primary key auto_increment, titulo varchar(150) not null, autor varchar(100) not null, anodepublicacao varchar(15) not null, categoria varchar(150) not null, local varchar(150) not null, imagem LONGBLOB not null, emprestado varchar(50), idLocador varchar(10));";
			stm.executeUpdate(usarDB);
			stm.executeUpdate(criarTabelaUsuario);
			stm.executeUpdate(criarTabelaLivros);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void inserirUsuario(Usuario usuario) {
		String insert = "INSERT INTO usuario(nome, email, senha, tipodeconta) VALUES (?, ?, ?, ?)";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(insert);
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setString(3, usuario.getSenha());
			pst.setString(4, usuario.getTipoDeConta());
			
			pst.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void inserirLivro(Livros livros) {
		String insert = "INSERT INTO livros(titulo, autor, anodepublicacao, categoria, local, imagem) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			Connection con = conectar();
			File imageFile = new File(livros.getImagemDeCapa());
			FileInputStream imFileInputStream = new FileInputStream(imageFile);
			PreparedStatement preparedStatement = con.prepareStatement(insert);
			preparedStatement.setString(1, livros.getTitulo());
			preparedStatement.setString(2, livros.getAutor());
			preparedStatement.setString(3, livros.getAnoDePublicacao());
			preparedStatement.setString(4, livros.getCategoria());
			preparedStatement.setString(5, livros.getLocal());
			preparedStatement.setBlob(6, imFileInputStream);
			preparedStatement.executeUpdate();
			con.close();
			imFileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Usuario> listarUsuarios(){
		ArrayList <Usuario> usuarios = new ArrayList<Usuario>();
		String select = "SELECT * FROM usuario";
		try {
			Connection con = conectar();
			
			PreparedStatement pst = con.prepareStatement(select);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String email = rs.getString(3);
				String senha = rs.getString(4);
				String tipoConta = rs.getString(5);
				
				usuarios.add(new Usuario(idcon, nome, email, senha, tipoConta));
			}
			con.close();
			return usuarios;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Livros> listarLivros(){
		ArrayList<Livros> livros = new ArrayList<Livros>();
		String select = "SELECT * FROM livros ORDER BY titulo";
		String selectUsuario = "SELECT nome FROM usuario WHERE idcon=?";
		try {
			Connection con = conectar();
			
			PreparedStatement preparedStatement = con.prepareStatement(select);
			ResultSet rSet = preparedStatement.executeQuery();
			
			while(rSet.next()) {
				String idcon = rSet.getString(1);
				String titulo = rSet.getString(2);
				String autor = rSet.getString(3);
				String anoDePublicacao = rSet.getString(4);
				String categoria = rSet.getString(5);
				String local = rSet.getString(6);
				Blob imagem = rSet.getBlob(7);
				InputStream inputStream = imagem.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesLidos = -1;
				while((bytesLidos = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesLidos);
				}
				byte[] imgBytes = outputStream.toByteArray();
				String imagemDB = Base64.getEncoder().encodeToString(imgBytes);
				inputStream.close();
				outputStream.close();
				String emprestado = rSet.getString(8);
				String idLocador = rSet.getString(9);
				PreparedStatement preparedStatement2 = con.prepareStatement(selectUsuario);
				preparedStatement2.setString(1, idLocador);
				ResultSet rSet2 = preparedStatement2.executeQuery();
				String nomeLocador = "";
				while (rSet2.next()) {
					nomeLocador = rSet2.getString(1);
				}
				livros.add(new Livros(idcon, titulo, autor, anoDePublicacao, categoria, local, imagemDB, emprestado, idLocador, nomeLocador));
			}
			con.close();
			return livros;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void setEmprestimo(Livros livros) {
		String update = "UPDATE livros SET emprestado='sim', idLocador=? WHERE idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement preparedStatement = con.prepareStatement(update);
			preparedStatement.setString(1, livros.getIdLocador());
			preparedStatement.setString(2, livros.getIdcon());
			preparedStatement.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Livros> listarLivrosDoUsuario(String idcon){
		ArrayList<Livros> livros = listarLivros();
		ArrayList<Livros> livrosDoUsuario = new ArrayList<Livros>();
		for (int i = 0; i < livros.size(); i++) {
			if (livros.get(i).getIdLocador() != null) {
				if (livros.get(i).getIdLocador().equals(idcon)) {
					livrosDoUsuario.add(new Livros(livros.get(i).getIdcon(), livros.get(i).getTitulo(), livros.get(i).getAutor(), livros.get(i).getAnoDePublicacao(), livros.get(i).getImagemDeCapa()));
				}
			}
		}
		return livrosDoUsuario;
	}
	
	public void devolucaoLivro(Livros livros) {
		String update = "UPDATE livros SET emprestado=null, idLocador=null WHERE idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement preparedStatement = con.prepareStatement(update);
			preparedStatement.setString(1, livros.getIdcon());
			preparedStatement.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean redefinirSenha(Usuario usuario) {
		String update = "UPDATE usuario SET senha=? WHERE email=?";
		ArrayList<Usuario> usuarios = listarUsuarios();
		for (Usuario emailUsuario: usuarios) {
			if (emailUsuario.getEmail().equals(usuario.getEmail())) {
				try {
					Connection con = conectar();
					PreparedStatement pStatement = con.prepareStatement(update);
					pStatement.setString(1, usuario.getSenha());
					pStatement.setString(2, usuario.getEmail());
					pStatement.executeUpdate();
					con.close();
					boolean validarEmail = true;
					return validarEmail;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		boolean validarEmail = false;
		return validarEmail;
	}
	
	public ArrayList<Livros> pesquisarLivro(String pesquisa){
		String selectUsuario = "SELECT nome FROM usuario WHERE idcon=?";
		ArrayList<Livros> livros = new ArrayList<Livros>();
		try {
			Connection con = conectar();
			PreparedStatement pStatement = con.prepareStatement("SELECT * FROM livros WHERE titulo LIKE concat('%', ('"+pesquisa+"'), '%')");
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				String idcon = rSet.getString(1);
				String titulo = rSet.getString(2);
				String autor = rSet.getString(3);
				String anoDePublicacao = rSet.getString(4);
				String categoria = rSet.getString(5);
				String local = rSet.getString(6);
				Blob imagem = rSet.getBlob(7);
				InputStream inputStream = imagem.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesLidos = -1;
				while((bytesLidos = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesLidos);
				}
				byte[] imgBytes = outputStream.toByteArray();
				String imagemDB = Base64.getEncoder().encodeToString(imgBytes);
				inputStream.close();
				outputStream.close();
				String emprestado = rSet.getString(8);
				String idLocador = rSet.getString(9);
				PreparedStatement preparedStatement2 = con.prepareStatement(selectUsuario);
				preparedStatement2.setString(1, idLocador);
				ResultSet rSet2 = preparedStatement2.executeQuery();
				String nomeLocador = "";
				while (rSet2.next()) {
					nomeLocador = rSet2.getString(1);
				}
				livros.add(new Livros(idcon, titulo, autor, anoDePublicacao, categoria, local, imagemDB, emprestado, idLocador, nomeLocador));
			}
			con.close();
			return livros;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
