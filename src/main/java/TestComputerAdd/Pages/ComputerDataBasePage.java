package TestComputerAdd.Pages;

import TestComputerAdd.WebDriverLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class ComputerDataBasePage {
    public WebDriver driver;
    WebDriverLogger logger = new WebDriverLogger();


    public ComputerDataBasePage(WebDriver driver) {

        PageFactory.initElements(driver, this);

        this.driver = driver;
    }

    @FindBy(xpath = "//*[@class='alert-message warning']//*[contains(text(),'Done')]")
    WebElement doneMassage;

    @FindBy(xpath = "//*[@id='searchbox']")
    WebElement setSearch;

    @FindBy(xpath = "//*[@value='Filter by name']")
    WebElement btnSearch;

    @FindBy(xpath = "//*[contains(text(), 'Nothing to display')]")
    WebElement msgNothingToDisplay;

    @FindBy(xpath = "//*[@id='add']")
    WebElement addNewPC;

    @FindBy(xpath = "//*[@class='computers zebra-striped']//tbody//tr")
    List<WebElement> tableRows;


    public void clickAddNewPC() {
        addNewPC.click();
    }


    public void findComputerInDataBase(String namePC) {
        setSearch.sendKeys(namePC);
        btnSearch.click();
    }


    public void checkDoneMessage(String namePC) {
        if (doneMassage.isDisplayed()) {
            logger.addToLog("Received message: Computer " + namePC + " has been created");
        } else {
            logger.addToLog("!!! Don't receive message: Computer " + namePC + " has been created");
        }
    }

    public boolean CheckComputerAddedInDataBase(String namePC, String introducedDate, String discountedDate, String company) {

        try {
            if (msgNothingToDisplay.isDisplayed()) {
                logger.addToLog("!!!Test fault   " + namePC + " in Computer database not found \n");
                System.out.println("Not catch");
            }
        } catch (RuntimeException e) {
            if (e.toString().contains("NoSuchElementException")) {
                System.out.println("catch");
                return tableLookup(namePC, introducedDate, discountedDate, company);
            }

        }
       return false;
    }

    private boolean tableLookup(String namePC, String introducedDate, String discountedDate, String company) {
        for (WebElement row : tableRows) {
            if (row.getText().equals(namePC + " " + introducedDate + " " + discountedDate + " " + company)) {
                System.out.println(row.getText());
                logger.addToLog("!!!Success   " + namePC + " in Computer database added \n");
                return true;
            }
        }
        logger.addToLog("!!!Test fault   " + namePC + " in Computer database not found \n");
        return false;
    }

}
