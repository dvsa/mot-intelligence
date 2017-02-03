package mot.intelligence.framework.config.webdriver;

import org.openqa.selenium.remote.RemoteWebDriver;

public class MotBrowserFactory {

     public static MotAppDriver createMotDriver(RemoteWebDriver remoteWebDriver){
        return new MotRemoteWebDriver(remoteWebDriver);

    }
}
