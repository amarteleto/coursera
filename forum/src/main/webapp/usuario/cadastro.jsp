<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>forum</title>
	<link rel="stylesheet" href="forum.css">
</head>
<body>
	<h1>forum</h1>
	<span id="lbMensagem" class="${mensagem_class}">${mensagem}</span>

	<form action="${pageContext.request.contextPath}/usuario/cadastro.slt" method="post">
		<table border="1">
			<tr>
				<td colspan="2" style="text-align: center">Cadastro de Usu�rio</td>
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
				<td style="text-align: right">email</td>
				<td><input type="text" id="txEmail" name="txEmail"></td>
			</tr>
			<tr>
				<td style="text-align: right">nome</td>
				<td><input type="text" id="txNome" name="txNome"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center">
					<input type="submit" id="btSalvar" value="salvar">
					<input type="button" id="btCancelar" onclick="location.href='${pageContext.request.contextPath}/login.jsp';" value="cancelar" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>