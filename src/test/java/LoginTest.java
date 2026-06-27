import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/login");
    }

    @Test(priority = 3)
    public void validLoginTest() {

        driver.findElement(By.id("username"))
                .sendKeys("tomsmith");

        driver.findElement(By.id("password"))
                .sendKeys("SuperSecretPassword!");

        driver.findElement(By.cssSelector("button[type='submit']"))
                .click();

        String message = driver.findElement(By.id("flash")).getText();

        Assert.assertTrue(message.contains("You logged into a secure area!"));
    }

    @Test(priority = 1)
    public void invalidLoginTest() {

        driver.findElement(By.id("username"))
                .sendKeys("wrongUser");

        driver.findElement(By.id("password"))
                .sendKeys("wrongPassword");

        driver.findElement(By.cssSelector("button[type='submit']"))
                .click();

        String message = driver.findElement(By.id("flash")).getText();

        Assert.assertTrue(message.contains("Your username is invalid!"));
    }

    @Test(priority = 2)
    public void emptyFieldLoginTest() {

        driver.findElement(By.cssSelector("button[type='submit']"))
                .click();

        String message = driver.findElement(By.id("flash")).getText();

        Assert.assertTrue(message.contains("Your username is invalid!"));
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}