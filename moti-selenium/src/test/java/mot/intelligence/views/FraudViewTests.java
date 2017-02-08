package mot.intelligence.views;

import org.testng.annotations.Test;

import java.io.IOException;

import mot.intelligence.DslTest;
import mot.intelligence.ui.pages.FraudPage;
import mot.intelligence.ui.pages.ReportPage;
import mot.intelligence.ui.pages.SummaryPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FraudViewTests extends DslTest {

    String reg = "registr";
    String locationName = "locationName";
    String locationAddress = "locationAddress";
    String personName = "personName";
    String personAddress = "personAddress";
    String comments = "comments";

    @Test(groups = {"BVT"})
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
        assertThat(summaryPage.allValuesWasPopulatedCorrectly(reg, locationName, locationAddress, personName, personAddress, comments), is(true));
    }

    @Test(groups = {"BVT"})
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
        assertThat(fraudPageAfterNavigation.allValuesWasPopulatedCorrectly(reg, locationName, locationAddress, personName, personAddress, comments), is(true));
    }

    @Test(groups = {"BVT"})
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
        assertThat(reportPage.confirmationMessageCorrect(), is(true));
    }
}
