<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String autenticarFalhou = (String) request.getAttribute("validar");
	if (autenticarFalhou == null){
		autenticarFalhou = "false";
	}
%>
<% String senhaAtualizada = (String) request.getAttribute("senhaAtualizada"); %>
<% if (senhaAtualizada == null){
		senhaAtualizada = "false";
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="styles/styleLogin.css">
</head>
<body>
    <div class="parteAzul">
        <h1>Faça login na Biblioteca</h1>
        <h2>Aplicativo para gerenciamento de livros</h2>
        <img src="img/LogoMarca.png" alt="LogoMarca Anote" class="imgSize">
        <img src="img/Imagem parte de baixo.png" alt="Pessoas conversando" class="img2Size">
    </div>
    <div class="fixar">
        <div class="blocoFormulario">
            <p class="bem-vindo">Bem-vindo de volta!</p>
            <h1 class="login">Login</h1>
            <% if (autenticarFalhou.equals("true")){ %>
            <p class="avisoLoginInvalido">Email ou Senha inválido(os)</p>
            <%} %>
            <% if (senhaAtualizada.equals("true")){ %>
            <p class="senhaAtualizada">Senha atualizada com sucesso!</p>
            <% } %>
            <form action="dashboard">
                <label for="iemail" class="email">Email</label>
                <input type="email" name="email" id="iemail" placeholder="Email">
                <label for="isenha" class="senha">Senha</label>
                <input type="password" name="senha" id="isenha" placeholder="Senha">
                <button type="submit">Logar</button>
            </form>
            <a href="esqueceu-senha.jsp" class="esquecisenha">Esqueceu sua senha?</a>
            <p class="conta">Não tem uma conta?</p>
            <a href="index.jsp" class="cadastrar">Cadastre-se</a>
        </div>
    </div>
</body>
</html>