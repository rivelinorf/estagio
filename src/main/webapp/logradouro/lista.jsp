<%@page import="br.com.sonner.estagio.model.Logradouro"%>
<%@page
	import="br.com.sonner.estagio.controller.LogradouroControllerImpl"%>
<%@page
	import="br.com.sonner.estagio.controller.api.LogradouroController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>title</title>
</head>
<body>
	<table border="1">
		<%
			LogradouroController logradouroController = new LogradouroControllerImpl();
		%>
		<%
			for (Logradouro logradouro : logradouroController.getAll()) {
		%>
		<tr>
			<td><%=logradouro.getId()%></td>
			<td><%=logradouro.getNome()%></td>
			<td><%=logradouro.getTipologradouro().getNome()%></td>
			<td><%=logradouro.getCidade().getNome()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>



