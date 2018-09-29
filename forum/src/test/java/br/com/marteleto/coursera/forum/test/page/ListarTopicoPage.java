package br.com.marteleto.coursera.forum.test.page;

import java.io.Serializable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ListarTopicoPage implements Serializable {
	private static final long serialVersionUID = 1L;
	private final WebDriver driver;
	@FindBy(how = How.ID, using = "btRanking")
    private WebElement btRanking;
	@FindBy(how = How.ID, using = "btNovo")
    private WebElement btNovo;
	@FindBy(how = How.ID, using = "lbMensagem")
    private WebElement lbMensagem;
	@FindBy(how = How.ID, using = "tdSemDados")
    private WebElement tdSemDados;
	@FindBy(how = How.NAME, using = "lkTopicos")
    private List<WebElement> links;

	public ListarTopicoPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get("http://localhost:8080/forum/topico/listar.slt");
	}
	
	public void novoCadastro() {
		btNovo.click();
	}
	
	public void acessarRanking() {
		btRanking.click();
	}
	
	public boolean semTopicos() {
		try {
			return tdSemDados.isDisplayed();
		} catch(NoSuchElementException ex) {
	         return false;
	    }
	}
	
	public boolean existeTopico(String titulo) {
		String result = driver.findElement(By.xpath(".//*[@id='tbResultado']//td[contains(.,'" + titulo + "')]")).getText();
		return (result != null && !"".equals(result.trim()));
	}
	
	public void acessarPrimeiroTopico() {
		if (links != null && !links.isEmpty()) {
			links.get(0).click();
		}
	}
	
	public Integer buscarIdPrimeiroTopico() {
		if (links != null && !links.isEmpty()) {
			return Integer.valueOf(links.get(0).getAttribute("idtopico").trim());
		}
		return null;
	}
}
