package br.com.marteleto.coursera.tradudor.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.marteleto.coursera.tradudor.util.TradutorUtil;

@WebServlet(value="/tradutor.slt")
public class TradutorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String chave = req.getParameter("txChave");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>resultado</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div>A resposta é: <b><span id='resultado'>" + TradutorUtil.traduzir(chave)  + "</span></b></div>");
		out.println("</body>");
		out.println("</html>");
	}
}
