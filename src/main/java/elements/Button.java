package elements;

import org.openqa.selenium.WebElement;

public class Button {
    WebElement button;

    public Button(WebElement element){
        button = element;
    }

    public void click(){
        button.click();
    }
}
