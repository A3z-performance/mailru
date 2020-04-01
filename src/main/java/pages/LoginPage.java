package pages;

import elements.Button;
import elements.TextEdit;
import fielddecorators.FieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {

    private WebDriver driver;

    @FindBy(id = "mailbox:login")
    private TextEdit mailInputField;

    @FindBy(id = "mailbox:domain")
    private WebElement mailboxSelect;

    @FindBy(id = "mailbox:submit")
    private Button submitBtn;

    @FindBy(id = "mailbox:password")
    private TextEdit passwordInputField;

    @FindBy(id = "PH_authLink")
    private  WebElement authLink;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(new FieldDecorator(driver), this);
        this.driver = driver;
    }

    public LoginPage inputMail(String mail){
        mailInputField.typeText(mail);
        return this;
    }

    public LoginPage selectMailbox(String mailbox){
        Select mailSelect = new Select(mailboxSelect);
        mailSelect.selectByVisibleText(mailbox);
        return this;
    }

    public LoginPage submitMail(){
        submitBtn.click();
        return this;
    }

    public LoginPage inputPassword(String password){
        passwordInputField.typeText(password);
        return this;
    }

    public MainMailPage login(){
        submitMail();
        return new MainMailPage(driver);
    }

    public WebElement getAuthLink(){
        return authLink;
    }

}
