package br.com.marteleto.coursera.forum.test.page;

import java.io.Serializable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import br.com.marteleto.coursera.forum.util.ConfigUtil;
import br.com.marteleto.coursera.forum.vo.Usuario;
import br.com.marteleto.coursera.selenium.util.WebDriverUtil;

public class CadastroUsuarioPage implements Serializable {
	private static final long serialVersionUID = 1L;
	private final WebDriver driver;
	@FindBy(how = How.ID, using = "txLogin")
    private WebElement txLogin;
	@FindBy(how = How.ID, using = "txSenha")
    private WebElement txSenha;
	@FindBy(how = How.ID, using = "txEmail")
    private WebElement txEmail;
	@FindBy(how = How.ID, using = "txNome")
    private WebElement txNome;
	@FindBy(how = How.ID, using = "btSalvar")
    private WebElement btSalvar;
	@FindBy(how = How.ID, using = "btCancelar")
    private WebElement btCancelar;
	@FindBy(how = How.ID, using = "lbMensagem")
    private WebElement lbMensagem;
		
	public CadastroUsuarioPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get(ConfigUtil.getSeleniumUrl() + "/usuario/cadastro.jsp");
	}
	
	public String cadastrar(Usuario usuario) {
		txLogin.sendKeys(usuario.getLogin());
        txSenha.sendKeys(usuario.getSenha());
        txEmail.sendKeys(usuario.getEmail());
        txNome.sendKeys(usuario.getNome());
        btSalvar.click();
        WebDriverUtil.waitForLoad();
		return lbMensagem.getText();
	}
	
	public void cancelar() {
		btCancelar.click();
		WebDriverUtil.waitForLoad();
	}
}
