<%@ page import="br.com.sonner.estagio.vos.EnderecoFiltroVO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="bairros"
	class="br.com.sonner.estagio.controller.BairroControllerImpl"></jsp:useBean>
<jsp:useBean id="logradouros"
	class="br.com.sonner.estagio.controller.LogradouroControllerImpl"></jsp:useBean>

<%
	EnderecoFiltroVO vo = (EnderecoFiltroVO) session.getAttribute("enderecoParaEditar");
	if (vo == null) {
		vo = new EnderecoFiltroVO();
		vo.setNumero(null);
		vo.setCep("");
		vo.setComplemento("");
		vo.setLogradouro(null);
		vo.setBairro(null);
	}
%>

<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Editar endereço" actionSalvar="true"
			formId="edit-form" actionFechar="true">
		</sge:header>

		<div class="div-form">
			<form action="/atualiza-endereco?id=<%=vo.getId()%>" method="post"
				id="edit-form" style="width: 100%">
				<div class="form-row">
					<div>Número:</div>
					<input type="text" name="numero" placeholder="Ex.: 111"
						class="form-control" value="<%=vo.getNumero()%>">
				</div>
				<div class="form-row">
					<div>Logradouro:</div>
                <select name="logradouro" class="form-control"
                        style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${logradouros.all}" var="logradouro">
                        <c:choose>
                            <c:when test="${logradouro.id == enderecoParaEditar.logradouro}">
                                <option value="${logradouro.id}" selected>${logradouro.tipologradouro.nome} ${logradouro.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${logradouro.id}">${logradouro.tipologradouro.nome} ${logradouro.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
				</div>
				<div class="form-row">
					<div>CEP:</div>
					<input type="text" name="cep" placeholder="Ex.: 00000-000"
						class="form-control" value="<%=vo.getCep()%>">
				</div>
				<div class="form-row">
					<div>Complemento:</div>
					<input type="text" name="complemento" class="form-control"
						class="form-control" value="<%=vo.getComplemento()%>">
				</div>
				<div class="form-row">
					<div>Bairro:</div>
                <select name="bairro" class="form-control"
                        style="background-color: rgb(46, 46, 46)">
                    <option value="">Selecione uma opção...</option>
                    <c:forEach items="${bairros.all}" var="bairro">
                        <c:choose>
                            <c:when test="${bairro.id == enderecoParaEditar.bairro}">
                                <option value="${bairro.id}" selected>${bairro.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${bairro.id}">${bairro.nome}</option>
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