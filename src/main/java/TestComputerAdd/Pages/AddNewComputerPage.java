package TestComputerAdd.Pages;

import TestComputerAdd.ConfProperties;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AddNewComputerPage {
    public WebDriver driver;

    public AddNewComputerPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    String namePC = ConfProperties.getProperty("namePC");
    String introducedDate = ConfProperties.getProperty("introducedDate");
    String discontinuedDate = ConfProperties.getProperty("discontinuedDate");
    String company = ConfProperties.getProperty("company");

    @FindBy(id = "name")
    private WebElement fillComputerName;

    @FindBy(xpath = "//*[@id='introduced']")
    private WebElement fillIntroduced;

    @FindBy(xpath = "//*[@id='discontinued']")
    private WebElement fillDiscontinued;

    @FindBy(xpath = "//*[@id='company']")
    private WebElement companyNameDropdown;

    @FindBy(xpath = "//*[@value='Create this computer']")
    private WebElement btnCreateThisPC;



    public void setPCName(String namePC) {
        fillComputerName.sendKeys(namePC);
    }

    public void setIntroducedDate(String introducedDate) {
        fillIntroduced.sendKeys(introducedDate);
    }

    public void setDiscontinuedDate(String discountedDate) {
        fillDiscontinued.sendKeys(discountedDate);
    }

    public void selectCompanyName(String company) {
        Select statusDropdown = new Select(companyNameDropdown);
        statusDropdown.selectByVisibleText(company);
    }

    public void clickCreateThisPC() {
        btnCreateThisPC.click();
    }

    public void fillForm() {
        waiting();
        setPCName(namePC);
        setIntroducedDate(introducedDate);
        setDiscontinuedDate(discontinuedDate);
        selectCompanyName(company);
        clickCreateThisPC();
        waiting();
    }

    private void waiting() {
        try {
            new WebDriverWait(driver, 1).until(
                    ExpectedConditions.jsReturnsValue("return document.readyState === 'complete' ? false : true"));
        } catch (TimeoutException ignored) {
        }
    }
}

