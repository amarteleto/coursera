package br.com.marteleto.coursera.forum.test.page;

import java.io.Serializable;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import br.com.marteleto.coursera.forum.business.TopicoBusiness;
import br.com.marteleto.coursera.forum.business.interfaces.ITopicoBusiness;
import br.com.marteleto.coursera.forum.vo.Topico;

public class ConsultarTopicoPage implements Serializable {
	private static final long serialVersionUID = 1L;
	private final WebDriver driver;
	@FindBy(how = How.ID, using = "btSalvar")
    private WebElement btSalvar;
	@FindBy(how = How.ID, using = "btVoltar")
    private WebElement btVoltar;
	@FindBy(how = How.ID, using = "lbMensagem")
    private WebElement lbMensagem;
	@FindBy(how = How.ID, using = "tdSemDados")
    private WebElement tdSemDados;
	@FindBy(how = How.ID, using = "lbQtdComentario")
    private WebElement lbQtdComentario;
	@FindBy(how = How.ID, using = "txComentario")
    private WebElement txComentario;
	
	public ConsultarTopicoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void acessarTopico(Integer topico) {
		this.driver.get("http://localhost:8080/forum/topico/consultar.slt?txTopico=" + topico);
	}
	
	public Integer buscarQtdComentarios() {
		return Integer.valueOf(lbQtdComentario.getText());
	}
	
	public void adicionarComentario (String comentario) {
		txComentario.sendKeys(comentario);
		btSalvar.click();
	}
}
