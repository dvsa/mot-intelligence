package uk.gov.dvsa.moti.views;

import org.testng.annotations.Test;

import java.io.IOException;

import uk.gov.dvsa.moti.BaseTest;
import uk.gov.dvsa.moti.ui.pages.FraudPage;
import uk.gov.dvsa.moti.ui.pages.ReportPage;
import uk.gov.dvsa.moti.ui.pages.SummaryPage;

import static org.testng.Assert.assertTrue;

public class FraudViewTests extends BaseTest {

    String reg = "registr";
    String locationName = "locationName";
    String locationAddress = "locationAddress";
    String personName = "personName";
    String personAddress = "personAddress";
    String comments = "comments";

    @Test
    public void summaryPageContainsEnteredFraudFormValues() throws IOException {
        //Given I am on the Fraud Page
        FraudPage fraudPage = pageNavigator.goToFraudPage();

        //When I submit fraud form
        SummaryPage summaryPage = fraudPage
                .addVehicleRegistration(reg)
                .addLocationName(locationName)
                .addLocationAddress(locationAddress)
                .addPersonName(personName)
                .addPersonAddress(personAddress)
                .addComments(comments)
                .clickContinue();

        //Then all values was populated correctly
        assertTrue(summaryPage.allValuesWasPopulatedCorrectly(reg, locationName, locationAddress, personName, personAddress, comments));
    }

    @Test
    public void whenIReturnToFormPageValuesWillBeSet() throws IOException {
        //Given I am on the Fraud Page
        FraudPage fraudPage = pageNavigator.goToFraudPage();

        //When I submit fraud form & co to Summary Page & return to fraud form
        FraudPage fraudPageAfterNavigation = fraudPage
                .addVehicleRegistration(reg)
                .addLocationName(locationName)
                .addLocationAddress(locationAddress)
                .addPersonName(personName)
                .addPersonAddress(personAddress)
                .addComments(comments)
                .clickContinue()
                .clickBackButton();

        //Then all values was populated correctly
        assertTrue(fraudPageAfterNavigation.allValuesWasPopulatedCorrectly(reg, locationName, locationAddress, personName, personAddress, comments));
    }

    @Test
    public void whenFormPopulatedCorrectlyReportWillBeSent() throws IOException {
        //Given I am on the Fraud Page
        FraudPage fraudPage = pageNavigator.goToFraudPage();

        //When I submit fraud form & co to Summary Page & submit
        ReportPage reportPage = fraudPage
                .addVehicleRegistration(reg)
                .addLocationName(locationName)
                .addLocationAddress(locationAddress)
                .addPersonName(personName)
                .addPersonAddress(personAddress)
                .addComments(comments)
                .clickContinue()
                .clickSendReportButton();

        //Then all values was populated correctly
        assertTrue(reportPage.confirmationMessageCorrect());
    }
}
