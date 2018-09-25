<%@ page import="br.com.sonner.estagio.vos.ProfessorFiltroVO" %>
<%@ page import="br.com.sonner.estagio.model.Funcionario" %>
<%@ page import="br.com.sonner.estagio.model.Escola" %>
<%@ page import="br.com.sonner.estagio.model.Pessoa" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="professors"
             class="br.com.sonner.estagio.controller.ProfessorControllerImpl"></jsp:useBean>
<jsp:useBean id="escolaController" class="br.com.sonner.estagio.controller.EscolaControllerImpl"></jsp:useBean>

<%
    ProfessorFiltroVO vo = (ProfessorFiltroVO) session.getAttribute("professorParaEditar");

    if (vo == null) {
        vo = new ProfessorFiltroVO();
        vo.setFuncionario(new Funcionario());
        vo.getFuncionario().setEscola(new Escola());
        vo.getFuncionario().setPessoa(new Pessoa());
    }
%>

<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
</head>
<body>
<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header titulo="Editar professor" actionSalvar="true"
                actionLimpar="/professor/preenche-vo"
                formId="edit-form" actionFechar="true">
    </sge:header>

    <div class="div-form">
        <form action="/atualiza-professor?id=<%=vo.getId()%>" method="post"
              id="edit-form">

            <input type="hidden" value="<%=vo.getId()%>" id="id">
            <div class="div-form">
                <div class="form-row">
                    <div>Nome:</div>
                    <input type="text" name="nome" class="form-control"
                           style="background-color: rgb(46, 46, 46)" value="<%=vo.getFuncionario().getPessoa().getNome()%>"
                           onkeypress="return validString(String.fromCharCode(window.event.keyCode))"
                           style="width: 460px;">
                </div>
                <div class="form-row">
                    <div>Data de nascimento:</div>
                    <input type="text" name="data-nascimento" class="form-control" placeholder="00/00/0000"
                           style="background-color: rgb(46, 46, 46); width: 100px;"
                           value="<%= vo.getFuncionario().getPessoa().getDataNascimento()%>">
                    <div style="width: 70px">CPF:</div>
                    <input type="text" name="cpf" class="form-control" placeholder="000.000.000-00"
                           style="background-color: rgb(46, 46, 46); width: 125px;" value="<%= vo.getFuncionario().getPessoa().getCpf()%>">
                </div>
                <div class="form-row">
                    <div>Pai:</div>
                    <input type="text" name="pai" class="form-control"
                           style="background-color: rgb(46, 46, 46)" value="<%=vo.getFuncionario().getPessoa().getPai()%>"
                           onkeypress="return validString(String.fromCharCode(window.event.keyCode))"
                           style="width: 460px;">
                </div>
                <div class="form-row">
                    <div>Mãe:</div>
                    <input type="text" name="mae" class="form-control"
                           style="background-color: rgb(46, 46, 46)" value="<%=vo.getFuncionario().getPessoa().getMae()%>"
                           onkeypress="return validString(String.fromCharCode(window.event.keyCode))">
                </div>
                <div class="form-row">
                    <div>Data de admissão:</div>
                    <input type="text" name="data-admissao" class="form-control"
                           style="background-color: rgb(46, 46, 46)" value="<%=vo.getFuncionario().getAdmissao()%>">
                </div>
                <div class="form-row">
                    <div>Escola:</div>
                    <select name="escola" class="form-control">
                        <option value="">Selecione uma opção...</option>
                        <c:forEach items="${escolaController.all}" var="escola">
                            <c:choose>
                                <c:when test="${escola.id == professorParaEditar.funcionario.escola.id}">
                                    <option value="${escola.id}" selected>${escola.nome}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${escola.id}">${escola.nome}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>