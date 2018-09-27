<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="br.com.marteleto.coursera.forum.vo.Topico" %>
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
	<table id="tbResultado" border="1">
		<tr>
			<td colspan="2" align="center">tópicos</td>
		</tr>
		<tr>
			<td align="center">título</td>
			<td align="center">criador</td>
		</tr>
		<%
			List<Topico> topicos = (List<Topico>) request.getAttribute("topicos");
			if (topicos.isEmpty()) {
		%>
				<td id="tdSemDados" colspan="2" align="center">Não existem tópicos a serem exibidos.</td>
		<%	
			} else {
				int cont = 0;
				for (Topico topico : topicos) {
		%>
					<tr id="trResultado<% out.println(cont); %>" name="trResultados">
						<td align='left'><% out.println(topico.getTitulo()); %></td>
						<td align='center'><% out.println(topico.getCriador().getNome()); %></td>
					</tr>
		<%
					cont++;
				}
			}
		%>
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="btNovo" onclick="location.href='${pageContext.request.contextPath}/topico/cadastro.jsp';" value="novo" />
				<input type="button" id="btRanking" onclick="location.href='${pageContext.request.contextPath}/usuario/ranking.slt';" value="ranking" />
			</td>
		</tr>
	</table>
</body>
</html>