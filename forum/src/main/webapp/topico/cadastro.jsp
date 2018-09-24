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

	<form action="${pageContext.request.contextPath}/topico/cadastro.slt" method="post">
		<table border="1">
			<tr>
				<td colspan="2" align="center">Cadastro de Tópico</td>
			</tr>
			<tr>
				<td align="right">título</td>
				<td><input type="text" id="txTitulo" name="txTitulo"></td>
			</tr>
			<tr>
				<td align="right">conteudo</td>
				<td><textarea id="txConteudo" name="txConteudo" rows="5" cols="40"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" id="btSalvar" value="salvar">
					<input type="button" onclick="location.href='${pageContext.request.contextPath}/topico/listar.slt';" value="cancelar" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>