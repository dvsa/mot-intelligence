package uk.gov.dvsa.moti.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import uk.gov.dvsa.moti.Page;
import uk.gov.dvsa.moti.framework.config.webdriver.MotAppDriver;
import uk.gov.dvsa.moti.helper.FormDataHelper;

public class ExamplePage extends Page {

    @FindBy(id = "lst-ib") private WebElement search;

    public ExamplePage(MotAppDriver driver) {
        super(driver);
        selfVerify();
    }

    @Override
    protected boolean selfVerify() {
        return true;
    }

    public void search(String text) {
        FormDataHelper.enterText(search, text);
    }
}
