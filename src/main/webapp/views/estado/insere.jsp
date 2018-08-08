<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="estados" class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>

<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>

<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header
            titulo="Inserir novo Estado"
            list="true"
            actionListar="/views/estado/lista.jsp"
            actionSalvar="/views/estado/insere.jsp">
    </sge:header>

    <div class="content">
        <form action="/insere-estado" method="post">
            <table class="novo">
                <tr>
                    <td align="right" width="200px">Nome: </td>
                    <td> <input type="text" name="nome" class="form-control"></td>
                </tr>
                <tr>
                    <td align="right">Abreviação: </td>
                    <td> <input type="text" name="abv" class="form-control"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><button class="main-btn">Enviar</button></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>