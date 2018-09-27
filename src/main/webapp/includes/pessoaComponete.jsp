<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="form-row">
    <div>Nome:</div>
    <input type="text" name="nome" class="form-control"
           style="background-color: rgb(46, 46, 46)" value="" placeholder="insira o nome completo"
           onkeypress="return validString(String.fromCharCode(window.event.keyCode))" style="width: 460px;">
</div>
<div class="form-row">
    <div>Sexo:</div>
    <select name="sexo" class="form-control">
        <option value="m">Masculino</option>
        <option value="f">Feminino</option>
    </select>
</div>
<div class="form-row">
    <div>Data de nascimento:</div>
    <input type="text" name="data-nascimento" class="form-control" placeholder="00/00/0000"
           style="background-color: rgb(46, 46, 46); width: 100px;" maxlength="10">

    <div style="width: 70px">CPF:</div>
    <input type="text" name="cpf" class="form-control" placeholder="000.000.000-00"
           style="background-color: rgb(46, 46, 46); width: 125px;" maxlength="14">
</div>
<div class="form-row">
    <div>Pai:</div>
    <input type="text" name="pai" class="form-control"
           style="background-color: rgb(46, 46, 46)" value=""
           onkeypress="return validString(String.fromCharCode(window.event.keyCode))" style="width: 460px;">
</div>
<div class="form-row">
    <div>Mãe:</div>
    <input type="text" name="mae" class="form-control"
           style="background-color: rgb(46, 46, 46)" value=""
           onkeypress="return validString(String.fromCharCode(window.event.keyCode))" style="width: 460px;">

</div>
