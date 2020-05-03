package pages;

import elements.Button;
import elements.MailContainer;
import elements.MailObject;
import fielddecorators.FieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class MainMailPage {
    protected WebDriver driver;

    //контейнер со списком писем
    private MailContainer mailContainer;

    // кнопка, отображающая текущий мэйл. Так же предоставляет доступ к найстройкам аккаунта
    @FindBy(id = "PH_user-email")
    private WebElement menuAuthBtn;

    //кнопка выхода из учетной записи
    @FindBy(id = "PH_logoutLink")
    private Button logoutBtn;

    // кнопка, при нажатии которой появляется форма для написания нового письма
    @FindBy(xpath = "//span[contains(text(), \"Написать письмо\")]")
    private Button createMsgBtn;

    // кнопка, при нажатии которой в главном контейнере показывается список входящих писем
    @FindBy(xpath = "//a[@href=\"/inbox/\"]")
    private Button inboxBtn;

    // кнопка, при нажатии которой в главном контейнере показывается список черновиков
    @FindBy(xpath = "//a[@href=\"/drafts/\"]")
    private Button draftBtn;

    // кнопка, при нажатии которой в главном контейнере показывается список отправленных писем
    @FindBy(xpath = "//a[@href=\"/sent/\"]")
    private Button sentBtn;

    // кнопка, при нажатии которой в главном контейнере показывается список писем корзины
    @FindBy(xpath = "//a[@href=\"/trash/\"]")
    private Button basketBtn;

    //контенер, содержащий списки(писем, черновиков и тд)
    @FindBy(xpath = "//div[@class=\"layout__main-frame\"]//div[@class=\"scrollable__container\"]")
    private WebElement mainScrollableContainer;

    @FindBy(xpath = "//div[@class=\"llc__content\"]")
    private List<WebElement> mailsList;


    public MainMailPage(WebDriver driver) {
        PageFactory.initElements(new FieldDecorator(driver), this);
        this.driver = driver;
    }

    public void openInbox() {
        inboxBtn.click();
        mailContainer = new MailContainer(driver);
    }

    public void openSent() {
        sentBtn.click();
        mailContainer = new MailContainer(driver);
    }

    public void openDrafts() {
        draftBtn.click();
        mailContainer = new MailContainer(driver);
    }

    public void openTrash() {
        basketBtn.click();
        mailContainer = new MailContainer(driver);
    }

    public TypeMailPage typeMail() {
        createMsgBtn.click();
        return new TypeMailPage(driver);
    }

    public FilledMailPage openMail(String reciever, String subject, String textMessage) {
        int pos = mailContainer.findMail(reciever, subject, textMessage);
        mailsList.get(pos).click();
        return new FilledMailPage(driver);
    }

    public MailObject getMail(String sendTo, String subject, String mailText) {
        return mailContainer.getMail(sendTo, subject, mailText);
    }

    public boolean containsMail(String sendTo, String subject, String mailText) {
        return mailContainer.containsMail(sendTo, subject, mailText);

    }

    public String getAccountMail() {
        return menuAuthBtn.getText();
    }

    public ExpectedCondition<WebElement> containerLoaded() {
        return ExpectedConditions.visibilityOf(mainScrollableContainer);
    }

    public LoginPage logout(){
        logoutBtn.click();
        return new LoginPage(driver);
    }
}
