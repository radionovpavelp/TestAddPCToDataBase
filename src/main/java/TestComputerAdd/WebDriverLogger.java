package TestComputerAdd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WebDriverLogger extends AbstractWebDriverEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverLogger.class);
    private String url;

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        LOGGER.info("WebDriver navigated to '" + url + "'");
        this.url = url;
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        LOGGER.info("WebDriver click on element - "
                + elementDescription(element));
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        if (!this.url.equals(driver.getCurrentUrl())) {
            LOGGER.info("WebDriver after click chang URL and go to '" + driver.getCurrentUrl() + "'");
            this.url = driver.getCurrentUrl();
        }
    }


    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        StringBuilder str = new StringBuilder(" value is: '");
        for (CharSequence i : keysToSend) {
            str.append(i);
        }
        str.append("'.");
        LOGGER.info("WebDriver will change value for element - "
                + elementDescription(element) + str);
    }

    public void addToLog(String addToLog) {
        LOGGER.info(addToLog);
    }

    private String elementDescription(WebElement element) {
        String description = "tag:" + element.getTagName();
        if (element.getAttribute("id") != null) {
            description += " id: " + element.getAttribute("id");
        } else if (element.getAttribute("name") != null) {
            description += " name: " + element.getAttribute("name");
        }
        if ((element.getText() != null)&&(element.getTagName().equals("option"))) description += " ('" + element.getText() + "')";

        return description;
    }
}