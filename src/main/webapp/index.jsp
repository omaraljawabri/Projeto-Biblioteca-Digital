<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro</title>
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="styles/style.css">
</head>
<body>
    <div class="parteAzul">
        <h1>Cadastre-se na Biblioteca</h1>
        <h2>Aplicativo para gerenciamento de livros</h2>
        <img src="img/LogoMarca.png" alt="LogoMarca Anote" class="imgSize">
        <img src="img/Imagem parte de baixo.png" alt="Pessoas conversando" class="img2Size">
    </div>
    <div class="fixar">
        <div class="blocoFormulario">
            <p class="bem-vindo">Bem-vindo ao aplicativo Biblioteca Digital</p>
            <h1 class="cadastro">Cadastro</h1>
            <form action="login">
                <label for="inomecompleto" class="nomecompleto">Nome completo</label>
                <input type="text" name="nome" id="inomecompleto" placeholder="Nome completo" required>
                <label for="iemail" class="email">Email</label>
                <input type="email" name="email" id="iemail" placeholder="Email" required>
                <label for="isenha" class="senha">Senha</label>
                <input type="password" name="senha" id="isenha" placeholder="Senha" required>
                <label for="iopcoesconta" class="opcoesconta">Tipo de conta</label>
                <select name="opcoesconta" id="iopcoesconta" required>
                    <option value="" disabled selected hidden>Selecione o tipo de conta</option>
                    <option value="admin">Administrador</option>
                    <option value="estudante">Estudante</option>
                </select>
                <button type="submit">Confirmar</button>
            </form>
            <p class="conta">Já possui uma conta?</p>
            <a href="login.jsp" class="logar">Entre na sua conta</a>
        </div>
    </div>
</body>
</html>