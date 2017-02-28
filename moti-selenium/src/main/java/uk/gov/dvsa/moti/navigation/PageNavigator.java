package uk.gov.dvsa.moti.navigation;

import java.io.IOException;

import uk.gov.dvsa.moti.framework.config.webdriver.MotAppDriver;
import uk.gov.dvsa.moti.ui.pages.FraudPage;

public class PageNavigator {

    private MotAppDriver driver;

    public void setDriver(MotAppDriver driver) {
        this.driver = driver;
    }

    public FraudPage goToFraudPage() throws IOException {
        navigateToPath(FraudPage.PATH);
        return new FraudPage(driver);
    }

    public void navigateToPath(String path) throws IOException {
        driver.navigateToPath(path);
    }
}
