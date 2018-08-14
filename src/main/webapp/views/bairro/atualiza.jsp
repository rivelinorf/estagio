<%@ page import="br.com.sonner.estagio.vos.BairroFiltroVO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="cidades"
	class="br.com.sonner.estagio.controller.CidadeControllerImpl"></jsp:useBean>

<%
	BairroFiltroVO vo = (BairroFiltroVO) session.getAttribute("bairroParaEditar");

	if (vo == null) {
		vo = new BairroFiltroVO();
		vo.setNome("");
		vo.setCidade(null);
	}
%>

<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Editar bairro" actionSalvar="true"
			formId="edit-form" actionFechar="true">
		</sge:header>

		<div class="div-form">
			<form action="/atualiza-bairro?id=<%=vo.getId()%>" method="post"
				id="edit-form" style="width: 100%">
				<div class="form-row">
					<div>Nome:</div>
					<input type="text" name="nome" class="form-control"
						value="<%=vo.getNome()%>">
				</div>
				<div class="form-row">
					<div>Cidade:</div>
                <select name="cidade" class="form-control"
                        style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${cidades.all}" var="cidade">
                        <c:choose>
                            <c:when test="${cidade.id == bairroParaEditar.cidade}">
                                <option value="${cidade.id}" selected>${cidade.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${cidade.id}">${cidade.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
