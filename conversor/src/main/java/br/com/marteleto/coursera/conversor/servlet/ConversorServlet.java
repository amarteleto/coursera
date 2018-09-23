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
		PrintWriter out = resp.getWriter(); 
		if (opcao.equals(Integer.valueOf(1))) {
			out.println("A resposta é: [" + ConversorUtil.converterFahrenheitParaCelsius(valor)  + "]");
		} else {
			out.println("A resposta é: [" + ConversorUtil.converterCelsiusParaFahrenheit(valor) + "]");
		}
	}
}
