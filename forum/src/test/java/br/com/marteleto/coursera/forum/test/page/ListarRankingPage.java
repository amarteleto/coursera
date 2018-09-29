package br.com.marteleto.coursera.forum.test.page;

import java.io.Serializable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import br.com.marteleto.coursera.forum.util.ConfigUtil;

public class ListarRankingPage implements Serializable {
	private static final long serialVersionUID = 1L;
	private final WebDriver driver;
	@FindBy(how = How.ID, using = "btTopicos")
    private WebElement btTopicos;
	@FindBy(how = How.ID, using = "lbMensagem")
    private WebElement lbMensagem;
	@FindBy(how = How.ID, using = "tdSemDados")
    private WebElement tdSemDados;
	@FindBy(how = How.NAME, using = "trResultados")
    private List<WebElement> trResultados;
		
	public ListarRankingPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get(ConfigUtil.getSeleniumUrl() + "/usuario/ranking.slt");
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
	
	public boolean existeRanking() {
		if (trResultados != null && !trResultados.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public Integer buscarPontosPorLogin(String login) {
		if (trResultados != null && !trResultados.isEmpty()) {
			for (WebElement trResultado: trResultados) {
				List<WebElement> tds = trResultado.findElements(By.tagName("td"));
				if (tds != null && !tds.isEmpty()) {
					if (tds.get(2).getText().equals(login)) {
						return Integer.valueOf(tds.get(0).getText().trim());
					}
				}
			}
		}
		return null;
	}
}
