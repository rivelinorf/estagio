
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/tipologradouro.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>

<body>
	<div class ="container">
		<div class="box-img">
			<img src="<%=request.getContextPath()%>/assets/imgs/logo.png">
		</div>
		<div class ="form-input">
			<form action="" method="post">
					 <input type="text" name="usuario"placeholder="usuario">
				<br> 
					<input type="password"name="senha" placeholder=" senha">
			</form>

		</div>
		<br>
		<a href="<%=request.getContextPath()%>/home.jsp"><button 
			class="btn btn-green">login</button></a>



	</div>
</body>

</html>