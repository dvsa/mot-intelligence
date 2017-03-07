package uk.gov.dvsa.moti;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import uk.gov.dvsa.moti.framework.config.webdriver.MotAppDriver;
import uk.gov.dvsa.moti.framework.elements.DvsaElementLocatorFactory;
import uk.gov.dvsa.moti.helper.PageInteractionHelper;

public abstract class Page {

    @FindBy(tagName = "h1") private WebElement title;
    protected MotAppDriver driver;

    public Page(final MotAppDriver driver) {
        this.driver = driver;
        DvsaElementLocatorFactory factory = new DvsaElementLocatorFactory(driver);
        PageFactory.initElements(factory, this);
        PageInteractionHelper.setDriver(driver);
    }

    public final String getTitle() {
        return title.getText();
    }

    protected abstract boolean selfVerify();

    @Override
    public final String toString() {
        return "Page: " + getTitle();
    }
}
