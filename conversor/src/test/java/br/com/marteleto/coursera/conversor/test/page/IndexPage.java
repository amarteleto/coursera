package br.com.marteleto.coursera.conversor.test.page;

import java.io.Serializable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import br.com.marteleto.coursera.conversor.test.util.ConfigUtil;

public class IndexPage implements Serializable {
	private static final long serialVersionUID = 1L;
	private final WebDriver driver;
	@FindBy(how = How.ID, using = "cbOpcao")
    private WebElement cbOpcaoWE;
	@FindBy(how = How.ID, using = "txValor")
    private WebElement txValor;
	@FindBy(how = How.ID, using = "btCalcular")
    private WebElement btCalcular;
	@FindBy(how = How.ID, using = "resultado")
    private WebElement resultado;
		
	public IndexPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get(ConfigUtil.getSeleniumUrl() + "/");
	}
	
	public Double converterCelsiusParaFahrenheit(Double celsius) {
		Select cbOpcao = new Select(cbOpcaoWE);
		cbOpcao.selectByIndex(1);
        txValor.sendKeys(celsius.toString());
        btCalcular.click();
		return Double.valueOf(resultado.getText().trim());
	}
	
	public Double converterFahrenheitParaCelsius(Double fahrenheit) {
		Select cbOpcao = new Select(cbOpcaoWE);
		cbOpcao.selectByIndex(0);
        txValor.sendKeys(fahrenheit.toString());
        btCalcular.click();
		return Double.valueOf(resultado.getText().trim());
	}
}
