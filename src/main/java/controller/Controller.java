package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.Livros;
import model.Usuario;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/Controller", "/login", "/dashboard", "/livro-inserido", "/meus-livros", "/meuslivros", "/dashboard-estudante", "/dashboard-estudante-livro-devolvido", "/login-senha-atualizada", "/dashboard-estudante-pesquisar", "/meus-livros-pesquisar"})
public class Controller extends HttpServlet {
	DAO dao = new DAO();
	Usuario usuario = new Usuario();
	Livros livros = new Livros();
	String idconUsuario;
	char primeiraLetraUsuario;
	String primeiroNomeDoUsuario;
	String primeiroNomeAdmin;
	char primeiraLetraAdmin;
	private static final long serialVersionUID = 1L;
	public Controller() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/login")) {
			adicionaUsuario(request, response);
		} else if (action.equals("/dashboard")) {
			autenticaLogin(request, response);
		} else if (action.equals("/livro-inserido")) {
			adicionaLivro(request, response);
		} else if (action.equals("/meus-livros")) {
			meusLivros(request, response);
		} else if (action.equals("/meuslivros")) {
			paginaMeusLivros(request, response);
		} else if (action.equals("/dashboard-estudante")) {
			paginaDashboardEstudante(request, response);
		} else if (action.equals("/dashboard-estudante-livro-devolvido")) {
			devolverLivro(request, response);
		} else if (action.equals("/login-senha-atualizada")) {
			atualizarSenha(request, response);
		} else if (action.equals("/dashboard-estudante-pesquisar")) {
			pesquisarLivroDashboard(request, response);
		} else if (action.equals("/meus-livros-pesquisar")) {
			pesquisarLivroMeusLivros(request, response);
		} else{
			response.sendRedirect("index.jsp");
		}
	}
	
	protected void adicionaUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("senha"));
		usuario.setTipoDeConta(request.getParameter("opcoesconta"));
		dao.inserirUsuario(usuario);
		response.sendRedirect("login.jsp");
	}
	
	protected void autenticaLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		ArrayList<Usuario> usuarios = dao.listarUsuarios();
		for (Usuario usuario: usuarios) {
			if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha) && usuario.getTipoDeConta().equals("estudante")) {
				primeiraLetraUsuario = usuario.getNome().charAt(0);
				String[] nomeUsuario = usuario.getNome().split(" ");
				primeiroNomeDoUsuario = nomeUsuario[0];
				idconUsuario = usuario.getIdcon();
				paginaDashboardEstudante(request, response);
				return;
			} else if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha) && usuario.getTipoDeConta().equals("admin")) {
				primeiraLetraAdmin = usuario.getNome().charAt(0);
				String[] nomeUsuario = usuario.getNome().split(" ");
				primeiroNomeAdmin = nomeUsuario[0];
				paginaDashboardAdmin(request, response);
				return;
			}
		}
		String autenticaoFalhou = "true";
		request.setAttribute("validar", autenticaoFalhou);
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}
	
	protected void adicionaLivro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		livros.setTitulo(request.getParameter("titulo"));
		livros.setAutor(request.getParameter("autor"));
		livros.setAnoDePublicacao(request.getParameter("ano"));
		livros.setCategoria(request.getParameter("categoria"));
		livros.setLocal(request.getParameter("local"));
		livros.setImagemDeCapa(request.getParameter("imagem"));
		dao.inserirLivro(livros);
		paginaDashboardAdmin(request, response);
	}
	
	protected void meusLivros(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		livros.setEstaEmprestado("sim");
		livros.setIdLocador(request.getParameter("pegarEmprestado"));
		livros.setIdcon(request.getParameter("idLivro"));
		dao.setEmprestimo(livros);
		paginaMeusLivros(request, response);
	}
	
	protected void devolverLivro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		livros.setIdcon(request.getParameter("devolver"));
		dao.devolucaoLivro(livros);
		paginaDashboardEstudante(request, response);
	}
	
	protected void atualizarSenha(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!request.getParameter("senha").equals(request.getParameter("confirmsenha"))) {
			String autenticaoFalhou = "true";
			request.setAttribute("senhasDiferentes", autenticaoFalhou);
			RequestDispatcher rd = request.getRequestDispatcher("esqueceu-senha.jsp");
			rd.forward(request, response);
		} else {
			usuario.setEmail(request.getParameter("email"));
			usuario.setSenha(request.getParameter("senha"));
			boolean validarEmail = dao.redefinirSenha(usuario);
			String senhaAtualizada = "true";
			if (!validarEmail) {
				String falhaEmail = "true";
				request.setAttribute("falhaEmail", falhaEmail);
				RequestDispatcher rDispatcher = request.getRequestDispatcher("esqueceu-senha.jsp");
				rDispatcher.forward(request, response);
				return;
			}
			request.setAttribute("senhaAtualizada", senhaAtualizada);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void pesquisarLivroDashboard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pesquisar = request.getParameter("pesquisa");
		ArrayList<Livros> livros = new ArrayList<Livros>();
		livros = dao.pesquisarLivro(pesquisar);
		request.setAttribute("primeiraLetra", primeiraLetraUsuario);
		request.setAttribute("nomeUsuario", primeiroNomeDoUsuario);
		request.setAttribute("idcon", idconUsuario);
		request.setAttribute("livros", livros);
		RequestDispatcher rd = request.getRequestDispatcher("dashboard-estudante.jsp");
		rd.forward(request, response);
	}
	
	protected void pesquisarLivroMeusLivros(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pesquisar = request.getParameter("pesquisa");
		ArrayList<Livros> livros = new ArrayList<Livros>();
		livros = dao.pesquisarLivro(pesquisar);
		request.setAttribute("primeiraLetra", primeiraLetraUsuario);
		request.setAttribute("primeiroNome", primeiroNomeDoUsuario);
		request.setAttribute("livrosDoUsuario", livros);
		RequestDispatcher rd = request.getRequestDispatcher("meus-livros.jsp");
		rd.forward(request, response);
	}
	
	protected void paginaMeusLivros(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Livros> livrosDoUsuario = dao.listarLivrosDoUsuario(idconUsuario);
		request.setAttribute("livrosDoUsuario", livrosDoUsuario);
		request.setAttribute("primeiraLetra", primeiraLetraUsuario);
		request.setAttribute("primeiroNome", primeiroNomeDoUsuario);
		RequestDispatcher rd = request.getRequestDispatcher("meus-livros.jsp");
		rd.forward(request, response);
	}
	
	protected void paginaDashboardEstudante(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("primeiraLetra", primeiraLetraUsuario);
		request.setAttribute("nomeUsuario", primeiroNomeDoUsuario);
		request.setAttribute("idcon", idconUsuario);
		ArrayList<Livros> livros = dao.listarLivros();
		request.setAttribute("livros", livros);
		RequestDispatcher rd = request.getRequestDispatcher("dashboard-estudante.jsp");
		rd.forward(request, response);
	}
	
	protected void paginaDashboardAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("primeiraLetra", primeiraLetraAdmin);
		request.setAttribute("nomeUsuario", primeiroNomeAdmin);
		RequestDispatcher rd = request.getRequestDispatcher("dashboard-admin.jsp");
		rd.forward(request, response);
	}

}
