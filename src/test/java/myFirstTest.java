import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class myFirstTest {
    public WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    static void setupClass () {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeTest
    void setupTest() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterTest
    void teardownTest() {
        //driver.quit();
    }

    @Test
    void orangeHrmTest() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        String title = driver.getTitle();

        Assert.assertTrue(title.contains("OrangeHRM"));

        By username = By.xpath("//input[@name='username']");
        By password = By.xpath("//input[@name='password']");
        By loginBtn = By.xpath("//button[contains(@class,'main orangehrm-login-button')]");

        driver.findElement(username).sendKeys("Admin");
        driver.findElement(password).sendKeys("admin123");
        driver.findElement(loginBtn).click();

        WebElement profilePicture = driver.findElement(By.xpath("//img[@alt='client brand logo']"));

        String url = driver.getCurrentUrl();
        String myUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        Assert.assertTrue(url.equals(myUrl));
    }
}
