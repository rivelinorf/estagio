<%@ page import="br.com.sonner.estagio.vos.FuncionarioFiltroVO" %>
<%@page contentType="text/html; charset=iso-8859-1"
        pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="funcionarios"
             class="br.com.sonner.estagio.controller.FuncionarioControllerImpl"></jsp:useBean>

<%
    FuncionarioFiltroVO vo = (FuncionarioFiltroVO) session.getAttribute("funcionario-para-editar");

    if (vo == null) {
        vo = new FuncionarioFiltroVO();
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
    <sge:header titulo="Editar funcionario" actionSalvar="true"
                actionLimpar="/funcionario/preenche-vo"
                formId="edit-form" actionFechar="true">
    </sge:header>

    <div class="div-form">
        <form action="/atualiza-funcionario?id=<%=vo.getId()%>" method="post"
              id="edit-form">

            <input type="hidden" value="<%=vo.getId()%>" id="id">
            <div class="div-form">
                <div class="form-row">
                    <div>Nome:</div>
                    <input type="text" name="nome" class="form-control"
                           style="background-color: rgb(46, 46, 46)" value="<%=vo.getPessoa().getNome()%>"
                           onkeypress="return validString(String.fromCharCode(window.event.keyCode))"
                           style="width: 460px;">
                </div>
                <div class="form-row">
                    <div>Data de nascimento:</div>
                    <input type="text" name="data-nascimento" class="form-control" placeholder="00/00/0000"
                           style="background-color: rgb(46, 46, 46); width: 100px;"
                           value="<%= vo.getPessoa().getDataNascimento()%>">
                    <div style="width: 70px">CPF:</div>
                    <input type="text" name="cpf" class="form-control" placeholder="000.000.000-00"
                           style="background-color: rgb(46, 46, 46); width: 125px;" value="">
                </div>
                <div class="form-row">
                    <div>Pai:</div>
                    <input type="text" name="pai" class="form-control"
                           style="background-color: rgb(46, 46, 46)" value="<%=vo.getPessoa().getPai()%>"
                           onkeypress="return validString(String.fromCharCode(window.event.keyCode))"
                           style="width: 460px;">
                </div>
                <div class="form-row">
                    <div>Mãe:</div>
                    <input type="text" name="mae" class="form-control"
                           style="background-color: rgb(46, 46, 46)" value="<%=vo.getPessoa().getMae()%>"
                           onkeypress="return validString(String.fromCharCode(window.event.keyCode))">
                </div>
                <div class="form-row">
                    <div>Data de admissão:</div>
                    <input type="text" name="data-admissao" class="form-control"
                           style="background-color: rgb(46, 46, 46)" value="<%=vo.getAdmissao()%>">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>