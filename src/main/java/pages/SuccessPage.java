package pages;

import elements.Button;
import fielddecorators.FieldDecorator;
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

    @FindBy(xpath = "//span[@title=\"Закрыть\"]")
    private Button closeBtn;

    public SuccessPage(WebDriver driver){
        PageFactory.initElements(new FieldDecorator(driver), this);
    }

    public String getSuccessMsg(){
        return firstSuccessPart.getText() + " " + secondSuccessPart.getText();
    }

    public String getRecieverMail(){
        String line = reciever.getText();
        if(line.contains("<")){
            String pattern = "<(.+)>";

            Pattern r = Pattern.compile(pattern);

            Matcher m = r.matcher(line);
            if (m.find( )) {
                return m.group(1);
            }else {
                return "NO MATCH";
            }
        }

        return line;
    }

    public void closeWindow(){
        closeBtn.click();
    }
}
