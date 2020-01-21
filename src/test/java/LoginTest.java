import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginTest {

    private String baseUrl = "https://cloud.certus.software";
    private String firefoxDriver = "C:\\Users\\haisu\\IdeaProjects\\LearnSelenium\\src\\drivers\\geckodriver.exe";
    private String chromeDriver = "C:\\Users\\haisu\\IdeaProjects\\LearnSelenium\\src\\drivers\\chromedriver.exe";
    private WebDriver driver;


    @BeforeTest
    @Parameters("browser")
    public void launchBrowser(String browser) throws Exception {
        //Check if parameter passed from TestNG is 'firefox'
        if(browser.equalsIgnoreCase("Firefox")){
            //create firefox instance
            System.setProperty("webdriver.gecko.driver", firefoxDriver);
            driver = new FirefoxDriver();
        }
        //Check if parameter passed as 'chrome'
        else if(browser.equalsIgnoreCase("Chrome")){
            //set path to chromedriver.exe
            System.setProperty("webdriver.chrome.driver", chromeDriver);
            //create chrome instance
            driver = new ChromeDriver();
        }

        else{
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }



    @BeforeMethod
    public void insertDataInFields(){
        driver.get(baseUrl);
        driver.findElement(By.id("username")).sendKeys("robert");
        driver.findElement(By.id("password")).sendKeys("robert");
        driver.findElement(By.id("customer_code")).sendKeys("1");
    }

    @Test
    public void verifyLoginFeature(){
        driver.findElement(By.id("loginButton")).click();
        List<WebElement> dashboardItems = driver.findElements(By.id("dashboard-link"));
        int expectedDashboardItems = 1;
        Assert.assertEquals(dashboardItems.size(), expectedDashboardItems);
    }


    @AfterTest
    public void terminateBrowser(){
        //driver.close();
    }
}
