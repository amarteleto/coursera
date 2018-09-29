package br.com.marteleto.coursera.test.page;

import java.io.Serializable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndexPage implements Serializable {
	private static final long serialVersionUID = 1L;
	private final WebDriver driver;
	private WebDriverWait wait;
	@FindBy(how = How.ID, using = "txChave")
    private WebElement txChave;
	@FindBy(how = How.ID, using = "btTraduzir")
    private WebElement btTraduzir;
		
	public IndexPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,30);
	}
	
	public String traduzir(String chave) {
        txChave.sendKeys(chave);
        btTraduzir.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("resultado")));
        WebElement resultado = driver.findElement(By.id("resultado"));
		return resultado.getText();
	}
}
