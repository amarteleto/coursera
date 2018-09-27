package br.com.marteleto.coursera.forum.test.page;

import java.io.Serializable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import br.com.marteleto.coursera.forum.vo.Topico;

public class CadastroTopicoPage implements Serializable {
	private static final long serialVersionUID = 1L;
	private final WebDriver driver;
	@FindBy(how = How.ID, using = "txTitulo")
    private WebElement txTitulo;
	@FindBy(how = How.ID, using = "txConteudo")
    private WebElement txConteudo;
	@FindBy(how = How.ID, using = "btSalvar")
    private WebElement btSalvar;
	@FindBy(how = How.ID, using = "btCancelar")
    private WebElement btCancelar;
	@FindBy(how = How.ID, using = "lbMensagem")
    private WebElement lbMensagem;
		
	public CadastroTopicoPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get("http://localhost:8080/forum/topico/cadastro.jsp");
	}
	
	public String cadastrar(Topico topico) {
		txTitulo.sendKeys(topico.getTitulo());
		txConteudo.sendKeys(topico.getConteudo());
        btSalvar.click();
		return lbMensagem.getText();
	}
	
	public void cancelar() {
		btCancelar.click();
	}
}
