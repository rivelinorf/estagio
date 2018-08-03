<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/senha.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container borda-login">
		<div class="box-img">
			<img src="<%=request.getContextPath()%>/assets/imgs/logo.png">
		</div>
		<h1>Redefinir Senha</h1>
		<div class="form-input">
			<form action="" method="post">
				<input class="input" type="text" name="nome" placeholder="insira seu nome">	<br><br> 
			 <br> <input class="input" type="email" name="nome"placeholder="insira seu email"> <br> <br> 
				<input class="login" type="submit" value="Enviar"><br>
			</form>

		</div>
	</div>
</body>
</html>