package mot.intelligence.navigation;

import java.io.IOException;

import mot.intelligence.framework.config.webdriver.MotAppDriver;
import mot.intelligence.ui.pages.FraudPage;

public class PageNavigator {

    private MotAppDriver driver;

    public void setDriver(MotAppDriver driver) {
        this.driver = driver;
    }

    public FraudPage goToFraudPage() throws IOException {
        injectOpenAmCookieAndNavigateToPath(FraudPage.PATH);
        return new FraudPage(driver);
    }

    public void injectOpenAmCookieAndNavigateToPath(String path) throws IOException {
        driver.navigateToPath(path);
    }
}
