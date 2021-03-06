<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="br.com.marteleto.coursera.forum.vo.*" %>
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

	<form action="${pageContext.request.contextPath}/comentario/cadastro.slt" method="post">
		<%
			Topico topico = (Topico) request.getAttribute("topico");
			List<Comentario> comentarios = (List<Comentario>) topico.getComentarios();
		%>
		<input type="hidden" id="txTopico" name="txTopico" value="<% out.println(topico.getId()); %>"/>
		<table border="1">
			<tr>
				<td colspan="2" style="text-align: center">T�pico</td>
			</tr>
			<tr>
				<td style="text-align: right">t�tulo</td>
				<td><% out.println(topico.getTitulo()); %></td>
			</tr>
			<tr>
				<td style="text-align: right">conteudo</td>
				<td><% out.println(topico.getConteudo()); %></td>
			</tr>
		</table>
		<br>
		<table border="1">
			<tr>
				<td colspan="2" style="text-align: center">Coment�rios: <span id="lbQtdComentario"><% out.println(comentarios.size()); %></span></td>
			</tr>
			<tr>
				<td style="text-align: center">Coment�rio</td>
				<td style="text-align: center">Criador</td>
			</tr>
			<%
				if (comentarios.isEmpty()) {
			%>
					<td id="tdSemDados" colspan="2" style="text-align: center">N�o existem coment�rios a serem exibidos.</td>
			<%	
				} else {
					for (Comentario comentario : comentarios) {
			%>
						<tr id="trResultado<% out.println(comentario.getId()); %>" name="trResultados">
							<td style="text-align: left"><% out.println(comentario.getConteudo()); %></td>
							<td style="text-align: center"><% out.println(comentario.getCriador().getNome()); %></td>
						</tr>
			<%
					}
				}
			%>
			<tr>
				<td style="text-align: center"><textarea id="txComentario" name="txComentario" rows="3" cols="40"></textarea></td>
				<td style="text-align: center"><input type="submit" id="btSalvar" value="salvar"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center">
					<input type="button" id="btVoltar" onclick="location.href='${pageContext.request.contextPath}/topico/listar.slt';" value="voltar" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>