package uk.gov.dvsa.moti.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import uk.gov.dvsa.moti.Page;
import uk.gov.dvsa.moti.framework.config.webdriver.MotAppDriver;
import uk.gov.dvsa.moti.helper.PageInteractionHelper;

public class SummaryPage extends Page {
    public static final String PATH = "/fraud/summary";
    private static final String PAGE_TITLE = "Review and send your report";

    @FindBy(id = "continue") private WebElement sendReportButton;
    @FindBy(id = "back") private WebElement backButton;
    @FindBy(xpath = "(//td[@class='key-value-list__value'])[position()=1]") private WebElement vehicleRegValue;
    @FindBy(xpath = "(//td[@class='key-value-list__value'])[position()=2]") private WebElement locationNameValue;
    @FindBy(xpath = "(//td[@class='key-value-list__value'])[position()=3]") private WebElement locationAddressValue;
    @FindBy(xpath = "(//td[@class='key-value-list__value'])[position()=4]") private WebElement personNameValue;
    @FindBy(xpath = "(//td[@class='key-value-list__value'])[position()=5]") private WebElement personAddressValue;
    @FindBy(xpath = "(//td[@class='key-value-list__value'])[position()=6]") private WebElement commentsValue;

    public SummaryPage(MotAppDriver driver) {
        super(driver);
        selfVerify();
    }

    @Override
    protected boolean selfVerify() {
        return PageInteractionHelper.verifyTitle(this.getTitle(), PAGE_TITLE);
    }

    private boolean isVehicleRegistrationCorrect(String vehicleRegistration) {
        return vehicleRegValue.getText().equals(vehicleRegistration);
    }

    private boolean isLocationNameCorrect(String locationName) {
        return locationNameValue.getText().equals(locationName);
    }

    private boolean isLocationAddressCorrect(String locationAddress) {
        return locationAddressValue.getText().equals(locationAddress);
    }

    private boolean isPersonNameCorrect(String personName) {
        return  personNameValue.getText().equals(personName);
    }

    private boolean isPersonAddressCorrect(String personAddress) {
        return personAddressValue.getText().equals(personAddress);
    }

    private boolean areCommentsCorrect(String comments) {
        return commentsValue.getText().equals(comments);
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

    public FraudPage clickBackButton() {
        backButton.click();
        return new FraudPage(driver);
    }

    public ReportPage clickSendReportButton() {
        sendReportButton.click();
        return new ReportPage(driver);
    }
}
