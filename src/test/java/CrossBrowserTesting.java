import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import static org.testng.AssertJUnit.assertTrue;


public class CrossBrowserTesting {
WebDriver driver;
boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless"));
@BeforeMethod
@Parameters("browser")
    public void setUp(String browser) throws MalformedURLException {
        switch (browser) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.setHeadless(isHeadless);
                driver = new ChromeDriver(options);
                System.out.println("hello :: 12");
//                DesiredCapabilities cap1 = new DesiredCapabilities();
//                cap1.setBrowserName(browser);
//                driver = new RemoteWebDriver(new URL("http://3.84.232.113:4444/wd/hub"),cap1);
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                break;

            case "edge":
                EdgeOptions options1 = new EdgeOptions();
                options1.addArguments("--headless");
                options1.setHeadless(isHeadless);
                driver = new EdgeDriver(options1);
//                DesiredCapabilities cap2 = new DesiredCapabilities();
//                cap2.setBrowserName(browser);
//                driver = new RemoteWebDriver(new URL("http://3.84.232.113:4444/wd/hub"),cap2);
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                break;
        }
    }
    @Test
    public void testLinks() {
        driver.get("https://www.bbc.co.uk");
        WebElement e = driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[3]/div/div[3]"));
        System.out.println(e.getText());
        System.out.println(driver.getCurrentUrl());
        assertTrue(driver.getCurrentUrl().contains("bbc"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
