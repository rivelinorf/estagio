<html>
<body>
<h2>SGE - Sistema de Gestão Educacional</h2>

<a href="/estado/insere.jsp"><button>Inserir estado</button></a><br>
<a href="/cidade/insere.jsp"><button>Inserir cidade</button></a>

<form action="/tipoLogradouro" method="post">
    TipoLogradouro: <input type="text" name="nome"> <br>
    <button>Enviar</button>

</form>

<form action="/adicionaBairro" method="post">
	<h3>Bairro</h3>
    Nome: <input type="text" name="nome"> <br>
    <button>Enviar</button>

</form>
</body>
</html>
