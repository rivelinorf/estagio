<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Title</title>
</head>
<body>
	<form action="/endereco-insere" method="post">
		<h3>Estado</h3>
		numero: <input type="text" name="numero"> <br> 
		cep: <input type="text" name="cep">
		id do logradouro: <input type="text" name="logradouro">
		id do bairro: <input type="text" name="bairro">
		<button>Enviar</button>
	</form>
</body>
</html>
