<html>
<body>
<h2>SGE - Sistema de Gestão Educacional</h2>
<form action="/estado-insere" method="post">
    <h3>Estado</h3>
    nome: <input type="text" name="nome"> <br>
    abreviacao: <input type="text" name="abv">
    id: <input type="number" name="id">
    <button>Enviar</button>
</form>

<form action="/cidade" method="post">
    <h3>Cidade</h3>
    nome: <input type="text" name="nome"> <br>
    codigo: <input type="text" name="cod">
    estado: <input type="text" name="estado">
    <button>Enviar</button>
</form>

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
