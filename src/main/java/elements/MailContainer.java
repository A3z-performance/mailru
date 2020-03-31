package elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MailUtils;

import java.util.List;

public class MailContainer {
    WebDriverWait driverWait;

    @FindBy(xpath = "//div[@class=\"scrollable__container\"]")
    private WebElement container;

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
        driverWait = new WebDriverWait(driver, 5);
        driverWait.until(ExpectedConditions.visibilityOf(container));
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
            String sendToStr = sendToList.get(i).getText();
            String subjectStr = subjectList.get(i).getText();
            String mailTextStr = mailTextList.get(i).getText();
            if (sendToStr.equals(sendTo)
                    && (subjectStr.equals(subject) || subjectStr.equals("Self: " + subject))
                    && MailUtils.checkMailMsg(mailTextStr, mailText)
            )
                return i;
        }
        return -1;
    }

}
