<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/index.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>

<body>
	<div class="container borda-login">
		<div class="box-img">
			<img src="<%=request.getContextPath()%>/assets/imgs/logo.png">
		</div>

		<div class="form-input">
			<form action="/usuario-logar" method="post">
				<input class="input" type="text" name="usuario" placeholder="Usuario"> <br>
				<input class="input" type="password" name="senha" placeholder="Senha">
				<br> <button class="login">login</button>
			</form>
		</div>

		<br>
		<a class="link" href="<%=request.getContextPath()%>/senha.jsp">Esqueceu sua senha ?</a><br><br>
		<a class="link" href="<%=request.getContextPath()%>/cadastro.jsp">Cadastrar-se</a>
	</div>
</body>

</html>