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


public class NewsDetailPageTest {

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
  public  void testNewsDetailPage()  {
        driver.get("https://2nhaber.com");

        WebElement button = driver.findElement(By.cssSelector(".elementor-widget-cmsmasters-search__popup-trigger-container"));
        button.click();

        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".elementor-widget-cmsmasters-search__field")));
        searchInput.sendKeys("İstanbul");

        WebElement form = driver.findElement(By.cssSelector(".elementor-widget-cmsmasters-search__form"));
        form.submit();
        // tüm haberler:
        List<WebElement> articlesList = driver.findElements(By.className("cmsmasters-blog__post"));

      /*  for (WebElement article : articlesList) {
            System.out.println(article.getText());
        }*/

        WebElement selectedArticle = articlesList.get(7);

        WebElement news = selectedArticle.findElement(By.cssSelector("a.cmsmasters-animation"));

  //   8.haber başlığını bir değişkene atadım.(istenilen haber o olduğu için)
        String selectedNewsTitle = news.getText();

        //sayfadaki tüm haber başlıklarıyla istenilen haber başlığı karşılaştırılır ve böylece istenilen haberi sırası ile değil dinamik olarak buluruz
        for(WebElement articles : articlesList )
        {
            WebElement newsLink = articles.findElement(By.cssSelector("a.cmsmasters-animation"));

            String newsTitle = newsLink.getText();

            if (newsTitle.equals(selectedNewsTitle)) {
                // eşleşme durumunda o haberin detay sayfasına gidilir
                String newsDetailPage = newsLink.getAttribute("href");
                driver.get(newsDetailPage);
                break;
            }
        }

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}