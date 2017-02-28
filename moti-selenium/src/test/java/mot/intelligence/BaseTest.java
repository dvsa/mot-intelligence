package mot.intelligence;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import mot.intelligence.framework.config.Configurator;
import mot.intelligence.framework.config.webdriver.MotAppDriver;
import mot.intelligence.framework.config.webdriver.WebDriverConfigurator;
import mot.intelligence.framework.listeners.TestListener;
import mot.intelligence.helper.Utilities.Logger;
import mot.intelligence.navigation.PageNavigator;

import java.text.SimpleDateFormat;
import java.util.Date;

@Listeners(TestListener.class)
public abstract class BaseTest {

    private MotAppDriver driver = null;
    protected static SimpleDateFormat screenshotDateFormat =
        new SimpleDateFormat("yyyyMMdd-HHmmss");

    private static final ThreadLocal<WebDriverConfigurator> webDriverConfigurator =
        new ThreadLocal<>();

    protected PageNavigator pageNavigator = new PageNavigator();

    @BeforeMethod(alwaysRun = true)
    public void setupBaseTest() {
        if (null == webDriverConfigurator.get()) {
            webDriverConfigurator.set(new WebDriverConfigurator());
        }

        driver = webDriverConfigurator.get().getDriver();
        pageNavigator.setDriver(driver);

        driver.setBaseUrl(Configurator.baseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.isSuccess()) {
            if (null != driver) {
                driver.manage().deleteAllCookies();
            }
        } else {
            WebDriverConfigurator cachedDriver = webDriverConfigurator.get();

            // Take screenshot on test failure
            if (cachedDriver != null && result.getStatus() == ITestResult.FAILURE && Configurator.isErrorScreenshotEnabled()) {
                driver.takeScreenShot(result.getTestClass().getName().replace("uk.gov.dvsa.ui", "")
                    + "." + result.getName() + "_" + screenshotDateFormat.format(new Date())
                    + ".png", Configurator.getErrorScreenshotPath() + "/" + Configurator.getBuildNumber());
            }

            if (null != cachedDriver) {
                Logger.LogError("Tearing down webdriver because of test failure");
                cachedDriver.destroy();
                webDriverConfigurator.set(null);
            }
            driver = null;
        }
    }
}
