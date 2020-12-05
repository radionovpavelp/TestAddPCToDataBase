package TestComputerAdd.Pages;

import TestComputerAdd.WebDriverLogger;
import org.openqa.selenium.By;
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


    @FindBy(xpath = "//*[@id='name']")
    private WebElement fillComputerName;

    @FindBy(xpath = "//*[@id='introduced']")
    private WebElement fillIntroduced;

    @FindBy(xpath = "//*[@id='discontinued']")
     private WebElement fillDiscontinued;

    @FindBy(xpath = "//*[@id='company']")
    private WebElement companyNameDropdown;

    @FindBy(xpath = "//*[@value='Create this computer']")
    private WebElement btnCreateThisPC;

    @FindBy(xpath = "//*[contains(text(), 'Add a computer')]")
    private WebElement checkPage;


    public void setPCName(String namePC) {
        fillComputerName.click();
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

    public void clickAddNewPC() {
       btnCreateThisPC.click();
    }

    public void fillForm(String namePC,String introducedDate,String discountedDate,String company)  {


        setPCName(namePC);
        setIntroducedDate(introducedDate);
        setDiscontinuedDate(discountedDate);
        selectCompanyName(company);
        clickAddNewPC();
    }


}

