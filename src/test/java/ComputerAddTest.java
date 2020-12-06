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

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new WebDriverLogger());
        dataBasePage = new ComputerDataBasePage(driver);
        addNewComputerPage = new AddNewComputerPage(driver);
        driver.manage().window().maximize();
        driver.get(ConfProperties.getProperty("computerDataBasePage"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testAddNewPc() {
        dataBasePage.clickAddNewPC();
        addNewComputerPage.fillForm();
        dataBasePage.checkDoneMessage();
        dataBasePage.findComputerInDataBase();
        boolean result = dataBasePage.checkComputerAddedInDataBase();
        Assert.assertTrue(result);
    }

    @AfterClass
    public void afterClass() throws IOException {
        driver.quit();
        Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
    }
}
