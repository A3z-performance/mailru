import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import utils.MailUtils;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class LoginTest {
    private static String url = "https://mail.ru/";
    private String mail = "perftest";
    private String mailbox = "@list.ru";
    private String password = "";
    private String reciever = "perftest@list.ru";
    private String subject = "Hello test!";
    private static String mailMessage;

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

        mailMessage = MailUtils.currentDate();
    }

    @Before
    public void getUrl(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
        driver.get(url);
    }

    @Test
    @DisplayName("Проверка авторизация в почте")
    public void loginTest(){
        String testMail = mail + mailbox;

        loginPage = new LoginPage(driver);

        mailPage = loginPage
                .inputMail(mail)
                .selectMailbox(mailbox)
                .submitMail()
                .inputPassword(password)
                .login();

        wait.until(mailPage.containerLoaded());

        String actualMail = mailPage.getAccountMail();
        Assert.assertEquals(testMail, actualMail);
    }

    @Test
    @DisplayName("Проверка выхода из почты")
    public void logoutTest(){
        loginTest();
        loginPage = mailPage.logout();
        assertTrue(loginPage.getAuthLink().isDisplayed());
    }

    @Test
    @DisplayName("Создание и проверка черновика")
    public void draftTest(){
        loginTest();

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
        assertTrue(mailPage.containsMail(reciever, subject, mailMessage));

        /**
         * Открытие черновика
         */
        filledMailPage = mailPage.openMail(reciever, subject, mailMessage);

        /**
         * Проверка корректности заполнения черновика
         */
        Assert.assertEquals(reciever, filledMailPage.getReciever());
        Assert.assertEquals(subject, filledMailPage.getSubject());
        Assert.assertEquals(mailMessage, filledMailPage.getTextMessage());
    }

    @Test
    @DisplayName("Проверка отправки письма")
    public void sendMailTest(){
        loginTest();

        /**
         * Открытие черновика
         */
        mailPage.openDrafts();
        filledMailPage = mailPage.openMail(reciever, subject, mailMessage);

        /**
         * Отправка письма и проверка окна успешной отправки письма
         */
        successPage = filledMailPage.SendMessage();
        Assert.assertEquals("Письмо отправлено", successPage.getSuccessMsg());
        Assert.assertEquals(reciever, successPage.getRecieverMail());
        successPage.closeWindow();

        /**
         * Письмо исчезло из черновиков
         */
        Assert.assertFalse(mailPage.containsMail(reciever, subject, mailMessage));

        /**
         * Проверка нахождения письма в отправленных
         */
        mailPage.openSent();
        assertTrue(mailPage.containsMail(reciever, subject, mailMessage));
    }


  //  @Test
    public void xloginTest(){

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
        assertTrue(mailPage.containsMail(reciever, subject, mailMessage));

        /**
         * Открытие черновика
         */
        filledMailPage = mailPage.openMail(reciever, subject, mailMessage);

        /**
         * Проверка корректности заполнения черновика
         */
        Assert.assertEquals(reciever, filledMailPage.getReciever());
        Assert.assertEquals(subject, filledMailPage.getSubject());
        Assert.assertEquals(mailMessage, filledMailPage.getTextMessage());

        /**
         * Отправка письма и проверка окна успешной отправки письма
         */
        successPage = filledMailPage.SendMessage();
        Assert.assertEquals("Письмо отправлено", successPage.getSuccessMsg());
        Assert.assertEquals(reciever, successPage.getRecieverMail());
        successPage.closeWindow();

        /**
         * Письмо исчезло из черновиков
         */
        Assert.assertFalse(mailPage.containsMail(reciever, subject, mailMessage));

        /**
         * Проверка нахождения письма в отправленных
         */
        mailPage.openSent();
        assertTrue(mailPage.containsMail(reciever, subject, mailMessage));

        /**
         * Выход из учетки
         */
        loginPage = mailPage.logout();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @After
    public void closeDriver() {
        driver.quit();
    }
}
