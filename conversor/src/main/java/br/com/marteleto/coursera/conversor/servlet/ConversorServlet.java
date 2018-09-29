package br.com.marteleto.coursera.conversor.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.marteleto.coursera.conversor.util.ConversorUtil;

@WebServlet(value="/conversor.slt")
public class ConversorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer opcao = Integer.valueOf(req.getParameter("cbOpcao"));
		Double valor = Double.valueOf(req.getParameter("txValor"));
		Double resultado = 0.0;
		if (opcao.equals(Integer.valueOf(1))) {
			resultado = ConversorUtil.converterFahrenheitParaCelsius(valor);
		} else {
			resultado = ConversorUtil.converterCelsiusParaFahrenheit(valor);
		}
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>resultado</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div>A resposta é: <b><span id='resultado'>" + resultado + "</span></b></div>");
		out.println("</body>");
		out.println("</html>");
	}
}
