<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>forum</title>
	<link rel="stylesheet" href="forum.css">
</head>
<body>
	<center><h1>forum</h1></center>
	<span id="lbMensagem" class="${mensagem_class}">${mensagem}</span>

	<form action="${pageContext.request.contextPath}/usuario/cadastro.slt" method="post">
		<table border="1">
			<tr>
				<td colspan="2" align="center">Cadastro de Usuário</td>
			</tr>
			<tr>
				<td align="right">login</td>
				<td><input type="text" id="txLogin" name="txLogin"></td>
			</tr>
			<tr>
				<td align="right">senha</td>
				<td><input type="text" id="txSenha" name="txSenha"></td>
			</tr>
			<tr>
				<td align="right">email</td>
				<td><input type="text" id="txEmail" name="txEmail"></td>
			</tr>
			<tr>
				<td align="right">nome</td>
				<td><input type="text" id="txNome" name="txNome"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" id="btSalvar" value="salvar">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>