<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String senhasDiferentes = (String) request.getAttribute("senhasDiferentes"); %>
<%
	if (senhasDiferentes == null){
		senhasDiferentes = "false";
	}
%>
<% String falhaEmail = (String) request.getAttribute("falhaEmail"); %>
<%
	if (falhaEmail == null){
		falhaEmail = "false";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Redefinir senha</title>
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="styles/styleEsqueceuSenha.css">
</head>
<body>
    <div class="parteAzul">
        <img src="img/LogoMarca.png" alt="LogoMarca Anote" class="imgSize">
    </div>
    <div class="fixar">
        <div class="blocoFormulario">
            <h1 class="esqueceusenha">Esqueceu a senha?</h1>
            <% if (senhasDiferentes.equals("true")){ %>
            <p class="senhasDiferentes">Os campos senha e confirmar senha devem ser iguais</p>
            <% } else if (falhaEmail.equals("true")){ %>
            <p class="falhaEmail">O e-mail digitado é inválido</p>
            <% } %>
            <form action="login-senha-atualizada">
                <label for="iemail" class="email">Email</label>
                <input type="email" name="email" id="iemail" placeholder="Email">
                <label for="isenha" class="senha">Nova senha</label>
                <input type="password" name="senha" id="isenha" placeholder="Nova senha">
                <label for="iconfirmsenha" class="confirmarsenha">Confirmar nova senha</label>
                <input type="password" name="confirmsenha" id="iconfirmsenha" placeholder="Confirmar nova senha" class="confirmarsenhainput">
                <button type="submit">Redefinir</button>
            </form>
        </div>
    </div>
</body>
</html>