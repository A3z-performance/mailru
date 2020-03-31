package elements;

import org.openqa.selenium.WebElement;

public class TextEdit {
    WebElement textEdit;

    public TextEdit(WebElement element){
        textEdit = element;
    }

    public void typeText(String text){
        textEdit.clear();
        textEdit.sendKeys(text);
    }

    public String getText(){
        return textEdit.getText();
    }

    public String getText(String attribute){
        return textEdit.getAttribute(attribute);
    }
}
