<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="model.Livros" %>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="java.util.ArrayList" %>
<% LocalTime time = LocalTime.now(); %>
<% DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); %>
<% String horaAtual = time.format(formatter); %>
<% DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd MMMM yyyy"); %>
<% LocalDate agora = LocalDate.now(); %>
<% String dataAtual = agora.format(formatter2); %>
<% ArrayList<Livros> livrosDoUsuario = (ArrayList<Livros>) request.getAttribute("livrosDoUsuario");%>
<% char primeiraLetra = (char) request.getAttribute("primeiraLetra"); %>
<% String primeiroNome = (String) request.getAttribute("primeiroNome"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Meus Livros</title>
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="styles/styleMeusLivros.css">
</head>
<body>
    <div class="barraLateral">
        <h1>Biblioteca Digital</h1>
        <a href="dashboard-estudante"><img src="img/home.png" alt="Home" class="imgHome"></a>
        <a href="dashboard-estudante"><p class="pHome">Home</p></a>
        <a href=""><img src="img/Bookshelf.png" alt="Estante de livros" class="imgBookshelf"></a>
        <a href=""><p class="pLivros">Meus Livros</p></a>
    </div>
    <div class="barraPesquisa">
        <form action="meus-livros-pesquisar">
            <input type="text" name="pesquisa" id="" placeholder="Pesquisar">
            <button type="submit" class="imgPesquisa"><img src="img/Search.png" alt=""></button>
        </form>
        <a href="meuslivros" class="limparFiltro">Limpar</a>
    </div>
    <div class="barraHorario">
        <img src="img/Relogio.png" alt="Relogio" class="imgRelogio">
        <p class="hora"><%=horaAtual %> HRS</p>
        <img src="img/Calendario.png" alt="Calendario" class="imgCalendario">
        <p class="data"><%=dataAtual %></p>
    </div>
    <div class="barraUsuario">
        <p class="bolaUsuario"><% out.println(primeiraLetra); %></p>
        <p class="nomeUsuario"><% out.println(primeiroNome); %></p>
        <a href="login.jsp"><img src="img/iconLogout.png" class="imgLogout"></img></a>
    </div>
    <h2 class="content-Body">Sua <span class="corAzul">estante</span></h2>
    <div class="container">
    <% for(int i = 0; i < livrosDoUsuario.size(); i++){ %>
        <div class="livroInfos">
            <img src="data:image/jpg;base64,<%= livrosDoUsuario.get(i).getImagemDeCapa() %>" alt="Capa Livro" class="capaLivro">
            <p class="tituloLivro"><%= livrosDoUsuario.get(i).getTitulo() %></p>
            <p class="autorAnoLivro"><%= livrosDoUsuario.get(i).getAutor() %>, <%= livrosDoUsuario.get(i).getAnoDePublicacao() %></p>
            <p class="botaoEmprestado">Emprestado</p>
            <form action="dashboard-estudante-livro-devolvido">
                <input type="text" name="devolver" value="<%= livrosDoUsuario.get(i).getIdcon() %>" style="display: none;">
                <button type="submit" class="botaoDevolver">Devolver</button>
            </form>
        </div>
   	<% } %>
    </div>
</body>
</html>