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
<% char primeiraLetra = (char) request.getAttribute("primeiraLetra"); %>
<% String primeiroNomeUsuario = (String) request.getAttribute("nomeUsuario"); %>
<% String idcon = (String) request.getAttribute("idcon"); %>
<% ArrayList<Livros> lista = (ArrayList<Livros>) request.getAttribute("livros"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="styles/styleEstudante.css">
</head>
<body>
    <div class="barraLateral">
        <h1>Biblioteca Digital</h1>
        <a href=""><img src="img/home.png" alt="Home" class="imgHome"></a>
        <a href=""><p class="pHome">Home</p></a>
        <a href="meuslivros"><img src="img/Bookshelf.png" alt="Estante de livros" class="imgBookshelf"></a>
        <a href="meuslivros"><p class="pLivros">Meus Livros</p></a>
    </div>
    <div class="barraPesquisa">
        <form action="dashboard-estudante-pesquisar">
            <input type="text" name="pesquisa" id="" placeholder="Pesquisar">
            <button type="submit" class="imgPesquisa"><img src="img/Search.png" alt=""></button>
        </form>
        <a href="dashboard-estudante" class="limparFiltro">Limpar</a>
    </div>
    <div class="barraHorario">
        <img src="img/Relogio.png" alt="Relogio" class="imgRelogio">
        <p class="hora"><%=horaAtual %> HRS</p>
        <img src="img/Calendario.png" alt="Calendario" class="imgCalendario">
        <p class="data"><%=dataAtual %></p>
    </div>
    <div class="barraUsuario">
        <p class="bolaUsuario"><% out.println(primeiraLetra); %></p>
        <p class="nomeUsuario"><% out.println(primeiroNomeUsuario); %></p>
        <a href="login.jsp"><img src="img/iconLogout.png" class="imgLogout"></img></a>
    </div>
    <div class="barraComInfos">
        <h3 class="posicaoTitulo">Título</h3>
        <h3 class="posicaoCategoria">Categoria</h3>
        <h3 class="posicaoStatus">Status</h3>
    </div>
    <% for(int i = 0; i < lista.size(); i++){ %>
    <% if (lista.get(i).getEstaEmprestado() == null && lista.get(i).getImagemDeCapa() != null){ %>
    <div class="fichasLivros">
        <p class="space 0"><img src="data:image/jpg;base64,<%= lista.get(i).getImagemDeCapa() %>" alt="" class="imgCapa"></p>
        <p class="space1"><%= lista.get(i).getTitulo() %> <br> <%= lista.get(i).getAutor() %>, <%= lista.get(i).getAnoDePublicacao() %></p>
        <p class="space2"><%= lista.get(i).getCategoria() %><p>
        <p class="space3"><span class="pegarEmprestado">Disponível</span> <br>  <span class="local"><%= lista.get(i).getLocal() %></span></p>
        <form action="meus-livros" class="space4">
            <input type="text" class="esconder" name="pegarEmprestado" value="<%= idcon %>">
            <input type="text" class="esconder" name="idLivro" value="<%= lista.get(i).getIdcon() %>">
            <button type="submit" class="bordaVerde">Pegar Emprestado</button>
        </form>
    </div>
    <% } else if(lista.get(i).getImagemDeCapa() != null){ %>
    <div class="fichasLivros">
        <p class="space 0"><img src="data:image/jpg;base64,<%= lista.get(i).getImagemDeCapa() %>" alt="" class="imgCapa"></p>
        <p class="space1"><%= lista.get(i).getTitulo() %> <br> <%= lista.get(i).getAutor() %>, <%= lista.get(i).getAnoDePublicacao() %></p>
        <p class="space2"><%= lista.get(i).getCategoria() %><p>
        <p class="space3"><span class="emprestado">Emprestado</span> <br>  <span class="pessoa"><%= lista.get(i).getNomeLocador() %></span> </p>
        <p class="space4"></p>
    </div>
    <% } %>
    <% } %>
</body>
</html>