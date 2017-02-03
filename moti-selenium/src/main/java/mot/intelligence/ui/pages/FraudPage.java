package mot.intelligence.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import mot.intelligence.Page;
import mot.intelligence.framework.config.webdriver.MotAppDriver;
import mot.intelligence.helper.FormDataHelper;

public class FraudPage  extends Page {
    public static final String PATH = "/fraud";
    private static final String PAGE_LEDE = "Give as much information as possible to help DVSA investigate.";

    @FindBy(className = "lede") private WebElement lede;
    @FindBy(id = "vehicleReg") private WebElement vehicleRegValue;
    @FindBy(id = "locationName") private WebElement locationNameValue;
    @FindBy(id = "locationAddress") private WebElement locationAddressValue;
    @FindBy(id = "personName") private WebElement personNameValue;
    @FindBy(id = "personAddress") private WebElement personAddressValue;
    @FindBy(id = "comments") private WebElement commentsValue;
    @FindBy(id = "continue") private WebElement continueButton;

    public FraudPage(MotAppDriver driver) {
        super(driver);
        selfVerify();
    }

    @Override
    protected boolean selfVerify() {
        return lede.getText().contains(PAGE_LEDE);
    }

    public FraudPage addVehicleRegistration(String vehicleRegistration) {
        FormDataHelper.enterText(vehicleRegValue, vehicleRegistration);
        return this;
    }

    private boolean isVehicleRegistrationCorrect(String vehicleRegistration) {
        return vehicleRegValue.getAttribute("value").equals(vehicleRegistration);
    }

    public FraudPage addLocationName(String locationName) {
        FormDataHelper.enterText(locationNameValue, locationName);
        return this;
    }

    private boolean isLocationNameCorrect(String locationName) {
        return locationNameValue.getAttribute("value").equals(locationName);
    }

    public FraudPage addLocationAddress(String locationAddress) {
        FormDataHelper.enterText(locationAddressValue, locationAddress);
        return this;
    }

    private boolean isLocationAddressCorrect(String locationAddress) {
        return locationAddressValue.getAttribute("value").equals(locationAddress);
    }

    public FraudPage addPersonName(String personName) {
        FormDataHelper.enterText(personNameValue, personName);
        return this;
    }

    private boolean isPersonNameCorrect(String personName) {
        return personNameValue.getAttribute("value").equals(personName);
    }

    public FraudPage addPersonAddress(String personAddress) {
        FormDataHelper.enterText(personAddressValue, personAddress);
        return this;
    }

    private boolean isPersonAddressCorrect(String personAddress) {
        return personAddressValue.getAttribute("value").equals(personAddress);
    }

    public FraudPage addComments(String comments) {
        FormDataHelper.enterText(commentsValue, comments);
        return this;
    }

    private boolean areCommentsCorrect(String comments) {
        return commentsValue.getAttribute("value").equals(comments);
    }

    public SummaryPage clickContinue() {
        continueButton.click();
        return new SummaryPage(driver);
    }



    public boolean allValuesWasPopulatedCorrectly(
            String vehicleRegistration,
            String locationName,
            String locationAddress,
            String personName,
            String personAddress,
            String comments
    ) {
        return isVehicleRegistrationCorrect(vehicleRegistration) &&
                isLocationNameCorrect(locationName) &&
                isLocationAddressCorrect(locationAddress) &&
                isPersonNameCorrect(personName) &&
                isPersonAddressCorrect(personAddress) &&
                areCommentsCorrect(comments);
    }
}
