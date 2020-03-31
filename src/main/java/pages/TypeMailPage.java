package pages;

import elements.TextEdit;
import fielddecorators.FieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TypeMailPage extends MailFrame{


    //Поле ввода получателя
    @FindBy(xpath = "//label//input[@type=\"text\"]")
    private TextEdit sendToInputField;

    //Поле ввода темы сообщения
    @FindBy(xpath = "//input[@name=\"Subject\"]")
    private TextEdit subjectInputField;

    //Поле ввода текста сообщения
    @FindBy(xpath = "//div[@role=\"textbox\"]/div[1]")
    private TextEdit mailInputField;


    public TypeMailPage(WebDriver driver) {
        PageFactory.initElements(new FieldDecorator(driver), this);
        this.driver = driver;
    }

    public void typeMessage(String sendTo, String subject, String mailText) {
        typeReciever(sendTo);
        typeSubject(subject);
        typeMessage(mailText);
    }

    private void typeReciever(String reciever) {
        sendToInputField.typeText(reciever);
    }

    private void typeSubject(String subject) {
        subjectInputField.typeText(subject);
    }

    private void typeMessage(String message) {
        mailInputField.typeText(message);
    }


}
