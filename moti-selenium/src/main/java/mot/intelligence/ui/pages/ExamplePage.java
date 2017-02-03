package mot.intelligence.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import mot.intelligence.Page;
import mot.intelligence.framework.config.webdriver.MotAppDriver;
import mot.intelligence.helper.FormDataHelper;

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
