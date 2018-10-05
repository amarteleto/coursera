package br.com.marteleto.coursera.forum.test.page;

import java.io.Serializable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import br.com.marteleto.coursera.forum.util.ConfigUtil;
import br.com.marteleto.coursera.selenium.util.WebDriverUtil;

public class LoginPage implements Serializable {
	private static final long serialVersionUID = 1L;
	private final WebDriver driver;
	@FindBy(how = How.ID, using = "txLogin")
    private WebElement txLogin;
	@FindBy(how = How.ID, using = "txSenha")
    private WebElement txSenha;
	@FindBy(how = How.ID, using = "btAutenticar")
    private WebElement btAutenticar;
	@FindBy(how = How.ID, using = "btNovo")
    private WebElement btNovo;
	@FindBy(how = How.ID, using = "lbMensagem")
    private WebElement lbMensagem;
		
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get(ConfigUtil.getSeleniumUrl() + "/login.jsp");
	}
	
	public String autenticar(String login, String senha) {
		txLogin.sendKeys(login);
        txSenha.sendKeys(senha);
        btAutenticar.click();
        WebDriverUtil.waitForLoad();
		return lbMensagem.getText();
	}
	
	public void novoCadastro() {
		btNovo.click();
		WebDriverUtil.waitForLoad();
	}
}
