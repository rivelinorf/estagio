<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/cadastro.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container borda-login">
		<div class="box-img">
			<img src="<%=request.getContextPath()%>/assets/imgs/logo.png">
		</div>
		<h1>Cadastro de Usuario</h1>
		<div class="form-input">
			<form action="" method="post">
				<input class="input" type="text" name="nome" placeholder="nome"><br>
				<br> <input class="input" type="data" name="data" placeholder="insira sua Data de Nascimento"> <br>
				<br> <input class="input" type="text" name="nomePai" placeholder="insira o nome do seu pai"> <br>
				<br> <input class="input" type="text" name="nomeMae" placeholder="insira o nome da sua mae"> <br>
				<br> <input class="input" type="text" name="endereco" placeholder="insira seu endereco"> <br>
				<br> <input class="input" type="email" name="nome" placeholder="insira seu email"> <br>
				<br> <input class="input" type="password" name="nome" placeholder="insira sua senha"> <br>
				<br> <input class="login" type="submit" value="Cadastrar"><br>
			</form>
		</div>
	</div>
</body>
</html>