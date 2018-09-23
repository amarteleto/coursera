<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="br.com.marteleto.coursera.forum.vo.Usuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>forum</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/forum.css">
</head>
<body>
	<center><h1>forum</h1></center>
	<span id="lbMensagem" class="${mensagem_class}">${mensagem}</span>
	<table border="1">
		<tr>
			<td colspan="3" align="center">ranking</td>
		</tr>
		<tr>
			<td align="center">pontos</td>
			<td align="center">nome</td>
			<td align="center">login</td>
		</tr>
		<%
			List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
			if (usuarios.isEmpty()) {
		%>
				<td colspan="3" align="center">Não existem usuarios a serem exibidos.</td>
		<%	
			} else {
				for (Usuario usuario : usuarios) {
		%>
					<tr>
						<td align='left'><% out.println(usuario.getPontos()); %></td>
						<td align='center'><% out.println(usuario.getNome()); %></td>
						<td align='center'><% out.println(usuario.getLogin()); %></td>
					</tr>
		<%
				}
			}
		%>
		<tr>
			<td colspan="3" align="center">
				<input type="button" onclick="location.href='${pageContext.request.contextPath}/topico/listar.slt';" value="tópicos" />
			</td>
		</tr>
	</table>
</body>
</html>