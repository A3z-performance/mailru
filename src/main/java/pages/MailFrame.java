package pages;

import elements.Button;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class MailFrame {

    WebDriver driver;

    @FindBy(xpath = "//button[@title=\"Закрыть\"]")
    private Button closeBtn;

    //Кнопка "Отправить"
    @FindBy(xpath = "//span[@title=\"Отправить\"]")
    private Button sendBtn;

    //Кнопка "Сохранить"
    @FindBy(xpath = "//span[@title=\"Сохранить\"]")
    private Button saveBtn;

    public void SaveMessage() {
        saveBtn.click();
        closePage();
    }

    public SuccessPage SendMessage() {
        sendBtn.click();
        return new SuccessPage(driver);
    }

    private void closePage(){
        closeBtn.click();
    }
}
