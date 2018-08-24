<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1"%>
<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/cadastro.css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Insert title here</title>
</head>
<body>
	<div class="container borda-login">
		<div class="box-img">
			<img src="<%=request.getContextPath()%>/assets/imgs/logo.png">
		</div>
		<h1>Cadastro de Usuario</h1>
		<div class="form-input">
			<form action="/usuario-insere" method="post">
				<input class="input" type="text" name="email" placeholder="insira seu email"><br>
				<br> <input class="input" type="text" name="usuario" placeholder="insira seu usuario"> <br>
				<br> <input class="input" type="password" name="senha" placeholder="insira sua senha"> <br>
				<br> <input class="login" type="submit" value="Cadastrar"><br>
			</form>
		</div>
	</div>
	<jsp:include page="/includes/msg.jsp"></jsp:include>
</body>
</html>