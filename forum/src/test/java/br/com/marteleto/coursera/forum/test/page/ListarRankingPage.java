package br.com.marteleto.coursera.forum.test.page;

import java.io.Serializable;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ListarRankingPage implements Serializable {
	private static final long serialVersionUID = 1L;
	private final WebDriver driver;
	@FindBy(how = How.ID, using = "btTopicos")
    private WebElement btTopicos;
	@FindBy(how = How.ID, using = "lbMensagem")
    private WebElement lbMensagem;
	@FindBy(how = How.ID, using = "tdSemDados")
    private WebElement tdSemDados;
		
	public ListarRankingPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get("http://localhost:8080/forum/usuario/ranking.slt");
	}
	
	public void acessarTopicos() {
		btTopicos.click();
	}
	
	public boolean semRanking() {
		try {
			return tdSemDados.isDisplayed();
		} catch(NoSuchElementException ex) {
	         return false;
	    }
	}
	
	public boolean existeUsuario(String nome) {
		String result = driver.findElement(By.xpath(".//*[@id='tbResultado']//td[contains(.,'" + nome + "')]")).getText();
		return (result != null && !"".equals(result.trim()));
	}
}
