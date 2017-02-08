package mot.intelligence.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import mot.intelligence.Page;
import mot.intelligence.framework.config.webdriver.MotAppDriver;

public class ReportPage extends Page {
    public static final String PATH = "/fraud/report";
    private static final String PAGE_TITLE = "Thank you";
    private String confirmationMessageText = "Your report has been sent";

    @FindBy(className = "transaction-header__next-step") private WebElement confirmationMessage;

    public ReportPage(MotAppDriver driver) {
        super(driver);
        selfVerify();
    }

    @Override
    protected boolean selfVerify() {
        return false;
    }

    public boolean confirmationMessageCorrect() {
        return confirmationMessage.getText().equals(confirmationMessageText);
    }
}
