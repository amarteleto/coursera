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
	<h1>forum</h1>
	<span id="lbMensagem" class="${mensagem_class}">${mensagem}</span>
	<form name="form" action="${pageContext.request.contextPath}/topico/consultar.slt" method="post">
		<input type="hidden" id="txTopico" name="txTopico"/>
	</form>
	<script type="text/javascript">
		function consultar(id) {
			var txTopico = document.getElementById('txTopico');
			txTopico.value = id;
			document.form.submit();
		}
	</script>
	<table id="tbResultado" border="1">
		<tr>
			<td colspan="2" style="text-align: center">tópicos</td>
		</tr>
		<tr>
			<td style="text-align: center">título</td>
			<td style="text-align: center">criador</td>
		</tr>
		<%
			List<Topico> topicos = (List<Topico>) request.getAttribute("topicos");
			if (topicos.isEmpty()) {
		%>
				<td id="tdSemDados" colspan="2" style="text-align: center">Não existem tópicos a serem exibidos.</td>
		<%	
			} else {
				for (Topico topico : topicos) {
		%>
					<tr id="trResultado<% out.println(topico.getId()); %>" name="trResultados">
						<td style="text-align: left"><a id="lkTopico<% out.println(topico.getId()); %>" idtopico="<% out.println(topico.getId()); %>" name="lkTopicos" href="javascript:consultar(<% out.println(topico.getId()); %>);"><% out.println(topico.getTitulo()); %></a></td>
						<td style="text-align: left"><% out.println(topico.getCriador().getNome()); %></td>
					</tr>
		<%
				}
			}
		%>
		<tr>
			<td colspan="2" style="text-align: center">
				<input type="button" id="btNovo" onclick="location.href='${pageContext.request.contextPath}/topico/cadastro.jsp';" value="novo" />
				<input type="button" id="btRanking" onclick="location.href='${pageContext.request.contextPath}/usuario/ranking.slt';" value="ranking" />
			</td>
		</tr>
	</table>
</body>
</html>