<%@page import="br.com.sonner.estagio.vos.TipologradouroFiltroVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="tipologradouros" class="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"></jsp:useBean>


<%
    TipologradouroFiltroVO vo = (TipologradouroFiltroVO) session.getAttribute("tipologradouro-para-editar");

    if (vo == null) {
        vo = new TipologradouroFiltroVO();
        vo.setNome("");

    }
%>
<html>
<head>
    <jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/includes/menu.jsp"></jsp:include>
<div class="main">
    <sge:header
            titulo="Editar Tipo de Logradouro"
            actionSalvar="true"
            formId="edit-form"
            actionFechar="true">
    </sge:header>
    <div class="content">
        <form action="/atualiza-tipologradouro?id=<%=vo.getId()%>" method="post" id="edit-form">
            <div class="div-form">
                <div class="form-row">
                    <div>Nome:</div>
                    <input type="text" name="tipologradouro" class="form-control" value="<%=vo.getNome()%>">
                </div>
            </div>

        </form>
    </div>
</div>
</body>
</html>