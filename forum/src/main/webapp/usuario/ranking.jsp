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
	<h1>forum</h1>
	<span id="lbMensagem" class="${mensagem_class}">${mensagem}</span>
	<table id="tbResultado" border="1">
		<tr>
			<td colspan="3" style="text-align: center">ranking</td>
		</tr>
		<tr>
			<td style="text-align: center">pontos</td>
			<td style="text-align: center">nome</td>
			<td style="text-align: center">login</td>
		</tr>
		<%
			List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
			if (usuarios.isEmpty()) {
		%>
				<td id="tdSemDados" colspan="3" style="text-align: center">Não existem usuarios a serem exibidos.</td>
		<%	
			} else {
				for (Usuario usuario : usuarios) {
		%>
					<tr name="trResultados">
						<td style="text-align: center"><% out.println(usuario.getPontos()); %></td>
						<td style="text-align: left"><% out.println(usuario.getNome()); %></td>
						<td style="text-align: left"><% out.println(usuario.getLogin()); %></td>
					</tr>
		<%
				}
			}
		%>
		<tr>
			<td colspan="3" style="text-align: center">
				<input type="button" id="btTopicos" onclick="location.href='${pageContext.request.contextPath}/topico/listar.slt';" value="tópicos" />
			</td>
		</tr>
	</table>
</body>
</html>