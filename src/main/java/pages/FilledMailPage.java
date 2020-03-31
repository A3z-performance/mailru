package pages;

import elements.TextEdit;
import fielddecorators.FieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FilledMailPage extends MailFrame{
    @FindBy(xpath = "//div[@data-type=\"to\"]//div[@data-type=\"to\"]//span")
    private TextEdit sendTo;

    @FindBy(xpath = "//input[@name=\"Subject\"]")
    private TextEdit subject;

    @FindBy(xpath = "//div[@role='textbox']//div[contains(@id, 'style')]/div/div[1]")
    private TextEdit textMessage;

    public FilledMailPage(WebDriver driver) {
        PageFactory.initElements(new FieldDecorator(driver), this);
        this.driver = driver;
    }

    public String getReciever(){
        return sendTo.getText();
    }

    public String getSubject(){
        return subject.getText("value");
    }

    public String getTextMessage(){
        return textMessage.getText();
    }
}
