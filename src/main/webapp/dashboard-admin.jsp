<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% char primeiraLetra = (char) request.getAttribute("primeiraLetra"); %>
<% String primeiroNomeUsuario = (String) request.getAttribute("nomeUsuario"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="styles/styleAdmin.css">
</head>
<body>
    <div class="barraLateral">
        <h1>Biblioteca Digital</h1>
        <a href=""><img src="img/IconeMais.png" alt="Icone de soma" class="imgMais"></a>
        <a href=""><p class="pAdicionar">Adicionar Livro</p></a>
    </div>
    <div class="barraUsuario">
        <p class="bolaUsuario"><% out.println(primeiraLetra); %></p>
        <p class="nomeUsuario"><% out.println(primeiroNomeUsuario); %></p>
         <a href="login.jsp"><img src="img/iconLogout.png" class="imgLogout"></img></a>
    </div>
    <div class="formulario">
        <h1>Adicione um livro</h1>
        <form action="livro-inserido">
            <label for="ititulo" class="titulo">Título</label>
            <input type="text" name="titulo" id="ititulo" placeholder="Título" class="inputTitulo" required>
            <label for="iautor" class="autor">Autor</label>
            <input type="text" name="autor" id="iautor" placeholder="Autor" class="inputAutor">
            <label for="iano" class="ano">Ano de publicação</label>
            <input type="text" name="ano" id="iano" placeholder="Ano de publicação" class="inputAno" required>
            <label for="icategoria" class="categoria">Categoria</label>
            <input type="text" name="categoria" id="icategoria" placeholder="Categoria" class="inputCategoria" required>
            <label for="ilocal" class="local">Local</label>
            <input type="text" name="local" id="ilocal" placeholder="Local" class="inputLocal" required>
            <label for="iimagem" class="imagem">Imagem de capa</label>
            <input type="file" name="imagem" id="iimagem" class="inputImagem" required>
            <button type="submit" class="botaoAdicionar">Adicionar</button>
        </form>
    </div>
</body>
</html>