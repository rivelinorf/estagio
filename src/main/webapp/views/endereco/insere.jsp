<%@ page import="br.com.sonner.estagio.vos.CidadeFiltroVO"%>
<%@ page import="br.com.sonner.estagio.vos.BairroFiltroVO"%>
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
	CidadeFiltroVO cidadevo = (CidadeFiltroVO) session.getAttribute("filtroCidade_insereEndereco");

	if (cidadevo == null) {
		cidadevo = new CidadeFiltroVO();
		cidadevo.setNome("");
		cidadevo.setSigla("");
		cidadevo.setCep("");
		cidadevo.setEstado(null);
	}
%>

<%
	BairroFiltroVO bairrovo = (BairroFiltroVO) session.getAttribute("filtroBairro_insereEndereco");

	if (bairrovo == null) {
		bairrovo = new BairroFiltroVO();
		bairrovo.setNome("");
		bairrovo.setCidade(null);
	}
%>

<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>

<script type="text/javascript" src="MascaraValidacao.js"></script>
</head>
<body>
	<jsp:include page="/includes/menu.jsp"></jsp:include>
	<div class="main">
		<sge:header titulo="Inserir novo Endereco" actionSalvar="true"
			formId="insere-form" actionFechar="true">
		</sge:header>

		<script>
		function mascaraInteiro(){
	        if (event.keyCode < 48 || event.keyCode > 57){
	                event.returnValue = false;
	                return false;
	        }
	        return true;
		}
		function MascaraCep(cep){
            if(mascaraInteiro(cep)==false){
            event.returnValue = false;
   	 	}       
    		return formataCampo(cep, '00.000-000', event);
		}
		
		function ValidaCep(cep){
	        exp = /\d{2}\.\d{3}\-\d{3}/
	        if(!exp.test(cep.value))
	                alert('Numero de Cep Invalido!');               
		}
		
		function formataCampo(campo, Mascara, evento) { 
	        var boleanoMascara; 

	        var Digitato = evento.keyCode;
	        exp = /\-|\.|\/|\(|\)| /g
	        campoSoNumeros = campo.value.toString().replace( exp, "" ); 

	        var posicaoCampo = 0;    
	        var NovoValorCampo="";
	        var TamanhoMascara = campoSoNumeros.length;; 

	        if (Digitato != 8) { // backspace 
	                for(i=0; i<= TamanhoMascara; i++) { 
	                        boleanoMascara  = ((Mascara.charAt(i) == "-") || (Mascara.charAt(i) == ".")
	                                                                || (Mascara.charAt(i) == "/")) 
	                        boleanoMascara  = boleanoMascara || ((Mascara.charAt(i) == "(") 
	                                                                || (Mascara.charAt(i) == ")") || (Mascara.charAt(i) == " ")) 
	                        if (boleanoMascara) { 
	                                NovoValorCampo += Mascara.charAt(i); 
	                                  TamanhoMascara++;
	                        }else { 
	                                NovoValorCampo += campoSoNumeros.charAt(posicaoCampo); 
	                                posicaoCampo++; 
	                          }              
	                  }      
	                campo.value = NovoValorCampo;
	                  return true; 
	        }else { 
	                return true; 
	        }
	}
	
		</script>

		<form action="/insere-endereco" method="get" id="filter-form"
			style="width: 100%;">

			<div class="form-row">
				<div>Estado:</div>
				<select name="estado" class="form-control"
					style="background-color: rgb(46, 46, 46)"
					onclick="location.href = '/insere-endereco?estado='+this.value">
					<c:forEach items="${estados.all}" var="estado">
						<c:choose>
							<c:when test="${estado.id == filtroCidade_insereEndereco.estado}">
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
					onclick="location.href = '/insere-endereco?cidade='+this.value">

					<c:forEach items="${listaCidade_insereEndereco}" var="cidade">
						<c:choose>
							<c:when test="${cidade.id == filtroBairro_insereEndereco.cidade}">
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
			<form name="form1" action="/insere-endereco" method="post"
				id="insere-form" style="width: 100%;">

				<div class="form-row">
					<div>Bairro:</div>
					<select name="bairro" class="form-control"
						style="background-color: rgb(46, 46, 46)">

						<c:forEach items="${listaBairro_insereEndereco}" var="bairro">
							<option value="${bairro.id}">${bairro.nome}</option>
						</c:forEach>
					</select>
				</div>

				<div class="form-row">
					<div>Tipo de Logradouro:</div>
					<select name="tipologradouro" class="form-control">

						<c:forEach items="${tipologradouros.all}" var="tipologradouro">
							<option value="${tipologradouro.id}">${tipologradouro.nome}</option>
						</c:forEach>
					</select>
				</div>

				<div class="form-row">
					<div>Logradouro:</div>
					<input type="text" name="logradouro" class="form-control">
				</div>

				<div class="form-row">
					<div>Número:</div>
					<input type="number" name="numero" placeholder="Ex.: 111"
						class="form-control">
				</div>

				<div class="form-row">
					<div>CEP:</div>
					<input type="text" name="cep" placeholder="Ex.: 00.000-000"
						class="form-control" onKeyPress="MascaraCep(form1.cep);"
						maxlength="10">
				</div>

				<div class="form-row">
					<div>Complemento:</div>
					<input type="text" name="complemento" placeholder="(opcional)"
						class="form-control">
				</div>
			</form>
		</div>
	</div>
</body>
</html>