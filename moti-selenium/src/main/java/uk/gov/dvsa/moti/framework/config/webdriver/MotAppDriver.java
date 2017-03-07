package uk.gov.dvsa.moti.framework.config.webdriver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

import uk.gov.dvsa.moti.helper.Utilities.Logger;

public abstract class MotAppDriver implements MotWebDriver {
    protected RemoteWebDriver remoteWebDriver;
    private String baseUrl = "";

    public MotAppDriver(RemoteWebDriver remoteWebDriver) {
        this.remoteWebDriver = remoteWebDriver;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void navigateToPath(String path) {
        remoteWebDriver.get(baseUrl + path);
    }

    public String getPageSource() {
        return this.remoteWebDriver.getPageSource();
    }

    public void takeScreenShot(String filename, String destinationPath) {
        try {
            File scrFile = remoteWebDriver.getScreenshotAs(OutputType.FILE);
            File screenshotFile = new File(destinationPath + "/" + filename);

            if (!screenshotFile.exists()) {
                FileUtils.copyFile(scrFile, screenshotFile);
                Logger.LogInfo("PageUrl: " + remoteWebDriver.getCurrentUrl());
                Logger.LogInfo("Screenshot saved to: " + screenshotFile.getAbsolutePath());
            }
        } catch (Exception e) {
           Logger.LogError("Error trying to take screen shot: " + e.getMessage(), e);
        }
    }
}
