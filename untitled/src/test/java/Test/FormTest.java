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
import org.junit.After;
import org.junit.Test;

public class FormTest {


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
    public  void testForm() {

        driver.get("https://www.2ntech.com.tr/hr");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys("NUR KILIÇ");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("birth"))).sendKeys("01-09-1998");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone"))).sendKeys("05069846802");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("hnk9833@gmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tcKimlik"))).sendKeys("12345678910");

       driver.findElement(By.xpath("//input[@id='cv_field']")).sendKeys("C:/Users/Asus/Documents/CV-Test Engineer.pdf");

        //bu alan otomatik seçili olduğu için yorum satırına aldım
      //  wait.until(ExpectedConditions.elementToBeClickable(By.id("pdp1"))).click();


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Lisans\"]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label=\"Go to the next step\"] "))).click();

        //2.form sayfasına eriştik:
        // Test Engineer başlıklı div'i seçiyoruz


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Test Engineer']"))).click();


     WebElement submitButton =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Gönder']")));
        if (submitButton != null) {
            System.out.println("submitButton var.");
            submitButton.click();
            System.out.println("form başarıyla gönderildi");
        } else {
            System.out.println("submitButton yok.");
        }
    }


    @After
    public void tearDown() {
        driver.quit();
    }

}

