<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>forum</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/forum.css">
</head>
<body>
	<h1>forum</h1>
	<span id="lbMensagem" class="${mensagem_class}">${mensagem}</span>
	<form action="${pageContext.request.contextPath}/usuario/autenticar.slt" method="post">
		<table border="1">
			<tr>
				<td colspan="2" style="text-align: center">identificação</td>
			</tr>
			<tr>
				<td style="text-align: right">login</td>
				<td><input type="text" id="txLogin" name="txLogin"></td>
			</tr>
			<tr>
				<td style="text-align: right">senha</td>
				<td><input type="text" id="txSenha" name="txSenha"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center">
					<input type="submit" id="btAutenticar" value="autenticar">
					<input type="button" id="btNovo" onclick="location.href='${pageContext.request.contextPath}/usuario/cadastro.jsp';" value="novo" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>