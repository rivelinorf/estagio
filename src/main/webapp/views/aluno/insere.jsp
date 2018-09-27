<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="alunoController"
             class="br.com.sonner.estagio.controller.AlunoControllerImpl"></jsp:useBean>
<jsp:useBean id="turmaController"
             class="br.com.sonner.estagio.controller.TurmaControllerImpl"></jsp:useBean>


<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
</head>
<body>
<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header titulo="Inserir novo Aluno" actionSalvar="true"
                formId="insere-form" actionFechar="true">
    </sge:header>
    <div class="div-form">
        <form action="/insere-aluno" method="post" id="insere-form"
              style="width: 60%; margin: auto">
            <jsp:include page="/includes/pessoaComponete.jsp"></jsp:include>
            <div class="form-row">
                <div>Escola:</div>
                <select name="escola" class="form-control">
                    <option value="">Selecione uma op��o...</option>
                    <c:forEach items="${escolaController.all}" var="escola">
                        <option value="${escola.id}">${escola.nome}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-row">
                <div>Turma:</div>
                <select name="turma" class="form-control">
                    <option value="">Selecione uma op��o...</option>
                    <c:forEach items="${turmaController.all}" var="turma">
                        <option value="${turma.id}">${turma.nome}</option>
                    </c:forEach>
                </select>
            </div>
        </form>
    </div>
</div>
</body>
</html>
<% session.setAttribute("campoAluno", null); %>