package elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.MailUtils;

import java.util.List;

public class MailContainer {

    @FindBy(xpath = "//div[@class=\"llc__content\"]")
    private List<WebElement> mailList;

    @FindBy(xpath = "//span[@class=\"ll-crpt\"]")
    private List<WebElement> sendToList;

    @FindBy(xpath = "//span[@class=\"llc__subject\"]/*")
    private List<WebElement> subjectList;

    @FindBy(xpath = "//span[@class=\"llc__snippet\"]/*")
    private List<WebElement> mailTextList;

    public MailContainer(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public MailObject getMail(String sendTo, String subject, String mailText) {
        int pos = findMail(sendTo, subject, mailText);
        if(pos != -1){
            return MailObject.newBuilder()
                    .setPosition(pos)
                    .setMail(sendTo)
                    .setSubject(subject)
                    .setMessage(mailText)
                    .build();
        } else return null;
    }

    public boolean containsMail(String sendTo, String subject, String mailText) {
        return (findMail(sendTo, subject, mailText) != -1);
    }

    public int findMail(String sendTo, String subject, String mailText){
        for (int i = 0; i < mailList.size(); i++) {
            if (sendToList.get(i).getText().equals(sendTo)
                    && subjectList.get(i).getText().equals(subject)
                    && MailUtils.checkMailMsg(mailTextList.get(i).getText(), mailText)
            )
                return i;
        }
        return -1;
    }

}
