import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FileUtils;
import utils.MailUtils;

import java.util.concurrent.TimeUnit;


public class ChromeTest extends  BaseTest{


//    @BeforeAll
//    public static void setupClass() {
//
//        password = FileUtils.getPassword("src/test/resources/password.txt");
//        mailMessage = MailUtils.currentDate();
//    }
    @BeforeEach
    public void getUrl(){
        WebDriverManager.chromedriver().version("80.0.3987.106").setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 6);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
        driver.get(url);
    }


}
