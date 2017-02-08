package mot.intelligence.helper;

import org.openqa.selenium.WebElement;

public class FormDataHelper {

    public static void enterText(WebElement webElement, String value) {
        webElement.click();
        webElement.clear();
        webElement.sendKeys(value);
    }
}
