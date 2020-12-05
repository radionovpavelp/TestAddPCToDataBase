import TestComputerAdd.ConfProperties;
import TestComputerAdd.Pages.AddNewComputerPage;
import TestComputerAdd.Pages.ComputerDataBasePage;
import TestComputerAdd.WebDriverLogger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ComputerAddTest {
    public static EventFiringWebDriver driver;
    public static ComputerDataBasePage dataBasePage;
    public static AddNewComputerPage addNewComputerPage;
  //  WebDriverLogger logger = new WebDriverLogger();

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new WebDriverLogger());
        dataBasePage = new ComputerDataBasePage(driver);
        addNewComputerPage = new AddNewComputerPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);
        driver.get(ConfProperties.getProperty("computerDataBasePage"));

    }
    @Test
    public void testAddNewPc() throws InterruptedException {
        String namePC = ConfProperties.getProperty("namePC");
        String introducedDate = ConfProperties.getProperty("introducedDate");
        String discountedDate = ConfProperties.getProperty("discountedDate");
        String company = ConfProperties.getProperty("company");
        dataBasePage.clickAddNewPC();
        Thread.sleep(1000);
        addNewComputerPage.fillForm(namePC, introducedDate, discountedDate, company);
        Thread.sleep(1000);
        dataBasePage.checkDoneMessage(namePC);
        dataBasePage.findComputerInDataBase(namePC);
        boolean a = dataBasePage.CheckComputerAddedInDataBase(namePC, introducedDate, discountedDate, company);
        Assert.assertTrue(a);

    }


    @AfterClass
    public void afterClass() throws IOException {
        //driver.quit();
       Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
    }
}
