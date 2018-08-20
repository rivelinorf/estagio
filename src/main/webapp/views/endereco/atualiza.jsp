<%@ page import="br.com.sonner.estagio.vos.EnderecoFiltroVO"%>
<%@ page import="br.com.sonner.estagio.vos.LogradouroFiltroVO"%>
<%@ page import="br.com.sonner.estagio.vos.CidadeFiltroVO" %>
<%@ page import="br.com.sonner.estagio.vos.BairroFiltroVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sge" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="bairros"
	class="br.com.sonner.estagio.controller.BairroControllerImpl"></jsp:useBean>
<jsp:useBean id="logradouros"
	class="br.com.sonner.estagio.controller.LogradouroControllerImpl"></jsp:useBean>
<jsp:useBean id="cidades"
	class="br.com.sonner.estagio.controller.CidadeControllerImpl"></jsp:useBean>
<jsp:useBean id="estados"
	class="br.com.sonner.estagio.controller.EstadoControllerImpl"></jsp:useBean>
<jsp:useBean id="tipologradouros"
	class="br.com.sonner.estagio.controller.TipoLogradouroControllerImpl"></jsp:useBean>
<%
	EnderecoFiltroVO vo = (EnderecoFiltroVO) session.getAttribute("enderecoParaEditar");
	LogradouroFiltroVO logradourovo = (LogradouroFiltroVO) session.getAttribute("filtroLogradouro");
	BairroFiltroVO bairrovo = (BairroFiltroVO) session.getAttribute("filtroBairro");
    CidadeFiltroVO cidadevo = (CidadeFiltroVO) session.getAttribute("filtroCidade");

	if (vo == null) {
		vo = new EnderecoFiltroVO();
		vo.setNumero(null);
		vo.setCep("");
		vo.setComplemento("");
		vo.setLogradouro(null);
		vo.setBairro(null);
	}

	if (logradourovo == null) {
		logradourovo = new LogradouroFiltroVO();
		logradourovo.setNome("");
		logradourovo.setCidade(null);
		logradourovo.setTipologradouro(null);
	}
	
    if (cidadevo == null) {
        cidadevo = new CidadeFiltroVO();
        cidadevo.setNome("");
        cidadevo.setSigla("");
        cidadevo.setCep("");
        cidadevo.setEstado(null);
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
		
		<form action="/atualiza-endereco" method="get" id="filter-form"
			style="width: 100%;">

			<div class="form-row">
				<div>Estado:</div>
				<select name="estado" class="form-control"
					style="background-color: rgb(46, 46, 46)"
					onclick="location.href = '/atualiza-endereco?estado='+this.value">
					<c:forEach items="${estados.all}" var="estado">
						<c:choose>
							<c:when test="${estado.id == filtroCidade.estado}">
								<option value="${estado.id}" selected>${estado.nome}</option>
							</c:when>
							<c:otherwise>
								<option value="${estado.id}">${estado.nome}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>

			<div class="form-row">
				<div>Cidade:</div>
				<select name="cidade" class="form-control"
					style="background-color: rgb(46, 46, 46)"
					onclick="location.href = '/atualiza-endereco?cidade='+this.value">
					<c:forEach items="${listaCidade}" var="cidade">
						<c:choose>
							<c:when test="${cidade.id == filtroBairro.cidade}">
								<option value="${cidade.id}" selected>${cidade.nome}</option>
							</c:when>
							<c:otherwise>
								<option value="${cidade.id}">${cidade.nome}</option>
							</c:otherwise>
						</c:choose>>

					</c:forEach>
				</select>
			</div>

		</form>

		<div class="div-form">
			<form action="/atualiza-endereco?id=<%=vo.getId()%>" method="post"
				id="edit-form" style="width: 100%">
				
				<div class="form-row">
					<div>Bairro:</div>
					<select name="bairro" class="form-control"
						style="background-color: rgb(46, 46, 46)">
						<c:forEach items="${listaBairro}" var="bairro">
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

				<div class="form-row">
					<div>Tipo de Logradouro:</div>
					<select name="tipologradouro" class="form-control"
						style="background-color: rgb(46, 46, 46)">
						<c:forEach items="${tipologradouros.all}" var="tipologradouro">
							<c:choose>
								<c:when
									test="${tipologradouro.id == logradouroParaEditar.tipologradouro}">
									<option value="${tipologradouro.id}" selected>${tipologradouro.nome}</option>
								</c:when>
								<c:otherwise>
									<option value="${tipologradouro.id}">${tipologradouro.nome}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>

            <div class="form-row">
                <div>Logradouro:</div>
                <input type="text" name="logradouro" class="form-control" value="<%= logradourovo.getNome() %>">
            </div>

				<div class="form-row">
					<div>Número:</div>
					<input type="number" name="numero" placeholder="Ex.: 111"
						class="form-control" value="<%=vo.getNumero()%>">
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



			</form>
		</div>
	</div>
</body>
</html>