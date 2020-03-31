package pages;

import elements.Button;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SuccessPage {
    @FindBy(xpath = "//div[@class=\"layer__header\"]/a")
    private WebElement firstSuccessPart;

    @FindBy(xpath = "//div[@class=\"layer__header\"]/span")
    private WebElement secondSuccessPart;

    @FindBy(xpath = "//div[@class=\"layer-sent-page__recipients-container\"]/span/span[2]")
    private WebElement reciever;

    @FindBy(xpath = "span[@class=\"button2__ico\"]")
    private Button closeBtn;

    public SuccessPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public String getSuccessMsg(){
        return firstSuccessPart.getText() + " " + secondSuccessPart.getText();
    }

    public String getRecieverMail(){
        String line = reciever.getText();
        String pattern = "<(.+)>";

        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(line);
        if (m.find( )) {
            return m.group(1);
        }else {
            return "NO MATCH";
        }
    }

    public void closeWindow(){
        closeBtn.click();
    }
}
