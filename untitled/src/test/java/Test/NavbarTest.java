package Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import org.junit.After;
import org.junit.Test;

public class NavbarTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
   public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testNavbarLinks() {

        driver.get("https://2nhaber.com");

        // sadece Ana Menüyü kapsar :  List<WebElement> menuItems = driver.findElements(By.xpath("//*[@id='menu-1-5dc673f1']/li"));

        // tüm childleri da kapsayan bütün menüler (dinamik olacak şekilde):
        List<WebElement> menuItems = driver.findElements(By.xpath("//*[@id='menu-1-5dc673f1']//li"));

        for (WebElement menuItem : menuItems) {
            WebElement menuLink = menuItem.findElement(By.tagName("a"));
            String menuUrl = menuLink.getAttribute("href");
            System.out.println("Menü: " + menuLink.getText() + " - " + menuUrl);
            wait.until(ExpectedConditions.elementToBeClickable(menuLink));
            menuLink.click();
            driver.navigate().back();

        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}