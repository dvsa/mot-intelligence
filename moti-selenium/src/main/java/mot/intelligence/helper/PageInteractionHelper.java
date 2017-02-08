package mot.intelligence.helper;

import mot.intelligence.exception.PageInstanceNotFoundException;
import mot.intelligence.framework.config.webdriver.MotAppDriver;

public class PageInteractionHelper {

    private static ThreadLocal<MotAppDriver> driver = new ThreadLocal<>();

    private PageInteractionHelper(MotAppDriver driver) {
        PageInteractionHelper.driver.set(driver);
    }

    public static PageInteractionHelper setDriver(MotAppDriver driver){
        return new PageInteractionHelper(driver);
    }

    public static MotAppDriver getDriver(){
        return driver.get();
    }


    public static boolean verifyTitle(String actual, String expected) {
        if(actual.contains(expected)){
            return true;
        }

        throw new PageInstanceNotFoundException("Page verification failed: "
                + String.format("\n Expected: %s page, \n Found: %s page", expected, actual)
        );
    }
}
