package TestComputerAdd.Pages;

import TestComputerAdd.ConfProperties;
import TestComputerAdd.WebDriverLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ComputerDataBasePage {
    public WebDriver driver;
    WebDriverLogger logger = new WebDriverLogger();
    String namePC = ConfProperties.getProperty("namePC");
    String introducedDate = ConfProperties.getProperty("introducedDate");
    String discountedDate = ConfProperties.getProperty("discountedDate");
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

    public boolean CheckComputerAddedInDataBase() throws ParseException {

        try {
            if (msgNothingToDisplay.isDisplayed()) {
                logger.addToLog("!!!Test fault   " + namePC + " in Computer database not found \n");

            }
        } catch (RuntimeException e) {
            if (e.toString().contains("NoSuchElementException")) {

                return tableLookup();
            }

        }
        return false;
    }

    private boolean tableLookup() throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("dd MMM yyyy",Locale.US);
        Date date = format1.parse(introducedDate);
        Date date1= format1.parse(discountedDate);
        introducedDate=format2.format(date);
        discountedDate= format2.format(date1);
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
