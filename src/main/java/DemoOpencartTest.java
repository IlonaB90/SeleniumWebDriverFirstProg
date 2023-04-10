
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class DemoOpencartTest {

    @Test
    public void WebsiteTest() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);

        String expectedPrice = "111.55€";
        String expectedName = "iMac";

        chromeDriver.get("https://demo.opencart.com/");

        final WebElement currency = chromeDriver.findElement(By.xpath //click currency
                ("//div[@class='dropdown']"));
        currency.click();

        final WebElement eur = chromeDriver.findElement(By.xpath // select currency Euro
                ("//a[@href='EUR']"));
        eur.click();

        final WebElement desktops = chromeDriver.findElement(By.xpath // click desktops
                ("//a[@class='nav-link dropdown-toggle']"));
        desktops.click();

        final WebElement mac = chromeDriver.findElement(By.xpath // select mac menu
                ("//a[@href='https://demo.opencart.com/index.php?route=product/category&language=en-gb&path=20_27']"));
        mac.click();


        String actualName = chromeDriver.findElement(By.xpath("//div[@class='description']//h4")).getText();// find name of product
        //System.out.println(actualName);

        String actualPrice = chromeDriver.findElement(By.xpath // find price of product
                ("//span[@class='price-new']")).getText();
        //System.out.println(actualPrice);

        assert expectedPrice.equals(actualPrice): "Product price is not 111.55€, is " + expectedPrice;
        assert expectedName.equals(actualName): "Product name is not iMac, is " + expectedName;

        chromeDriver.quit();
    }
}
