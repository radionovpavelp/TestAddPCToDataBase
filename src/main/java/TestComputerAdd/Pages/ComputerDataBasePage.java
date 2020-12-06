package TestComputerAdd.Pages;

import TestComputerAdd.ConfProperties;
import TestComputerAdd.WebDriverLogger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;


public class ComputerDataBasePage {
    public WebDriver driver;
    WebDriverLogger logger = new WebDriverLogger();
    String namePC = ConfProperties.getProperty("namePC");
    String introducedDate = ConfProperties.getProperty("introducedDate");
    String discontinuedDate = ConfProperties.getProperty("discontinuedDate");
    String company = ConfProperties.getProperty("company");


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


    public void findComputerInDataBase() {
        setSearch.sendKeys(namePC);
        btnSearch.click();
    }


    public void checkDoneMessage() {
        if (doneMassage.isDisplayed()) {
            logger.addToLog("Received message: Computer " + namePC + " has been created");
        } else {
            logger.addToLog("!!! Don't receive message: Computer " + namePC + " has been created");
        }
    }

    public boolean checkComputerAddedInDataBase() {
        try {
            if (msgNothingToDisplay.isDisplayed()) {
                logger.addToLog("!!!Test fault   " + namePC + " in Computer database not found \n");
            }
        } catch (NoSuchElementException e) {
                return tableLookup();
        }
        return false;
    }

    private boolean tableLookup() {
        for (WebElement row : tableRows) {
            if (row.getText().equals(namePC + " " + changeDateFormatForSearch(introducedDate) + " " + changeDateFormatForSearch(discontinuedDate) + " " + company)) {
                logger.addToLog("!!!Success   " + namePC + " in Computer database added \n");
                return true;
            }
        }
        logger.addToLog("!!!Test fault   " + namePC + " in Computer database not found \n");
        return false;
    }

    private String changeDateFormatForSearch(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.US);
        LocalDate localDate = LocalDate.parse(date);
        return formatter.format(localDate);
    }

}
