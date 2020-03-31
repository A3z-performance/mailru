import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    private static String url = "https://mail.ru/";
    private String mail = "perftest";
    private String mailbox = "@list.ru";
    private String password = "";
    private String reciever = "perftest@list.ru";
    private String subject = "Hello test!";
    private String mailMessage = "It's a test message";

    private static WebDriver driver;
    private static WebDriverWait wait;

    private LoginPage loginPage;
    private MainMailPage mailPage;
    private TypeMailPage typeMailPage;
    private FilledMailPage filledMailPage;
    private SuccessPage successPage;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
        driver.get(url);
    }

    @Test
    public void loginTest() throws InterruptedException {

        String testMail = mail + mailbox;


        loginPage = new LoginPage(driver);

        /**
         * Вход в почту
         */
        mailPage = loginPage
                .inputMail(mail)
                .selectMailbox(mailbox)
                .submitMail()
                .inputPassword(password)
                .login();

        wait.until(mailPage.containerLoaded());

        /**
         * проверка логина после входа
         */
        String actualMail = mailPage.getAccountMail();
        Assert.assertEquals(testMail, actualMail);

        /**
         * написать письмо, открыть входящие, открыть черновики
         */
        typeMailPage = mailPage.typeMail();
        typeMailPage.typeMessage(reciever, subject, mailMessage);
        typeMailPage.SaveMessage();
        mailPage.openInbox();
        mailPage.openDrafts();

        /**
         * Проверка отображение созданного письма в списке черновиков
         */
        Assert.assertTrue(mailPage.containsMail(reciever, subject, mailMessage));

        /**
         * Открытие черновика
         */
        filledMailPage = mailPage.openMail(reciever, subject, mailMessage);

        /**
         * Проверка корректности заполнения черновика
         */
        Assert.assertEquals(filledMailPage.getReciever(), reciever);
        Assert.assertEquals(filledMailPage.getSubject(), subject);
        Assert.assertEquals(filledMailPage.getTextMessage(), mailMessage);

        /**
         * Отправка письма и проверка окна успешной отправки письма
         */
        successPage = filledMailPage.SendMessage();
        Assert.assertEquals(successPage.getSuccessMsg(), "Письмо отправлено");
        Assert.assertEquals(successPage.getRecieverMail(), reciever);
        successPage.closeWindow();

        /**
         * Письмо исчезло из черновиков
         */
        Assert.assertFalse(mailPage.containsMail(reciever, subject, mailMessage));

        /**
         * Проверка нахождения письма в отправленных
         */
        mailPage.openSent();
        Assert.assertTrue(mailPage.containsMail(reciever, subject, mailMessage));

        /**
         * Выход из учетки
         */
        loginPage = mailPage.logout();
        Thread.sleep(10000);

    }

    @After
    public void closeDriver() {
        driver.quit();
    }
}
