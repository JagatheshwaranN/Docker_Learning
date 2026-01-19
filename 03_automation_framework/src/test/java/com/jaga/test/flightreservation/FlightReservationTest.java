package com.jaga.test.flightreservation;

import com.jaga.test.AbstractTest;
import com.jaga.pages.flightreservation.*;
import com.jaga.test.flightreservation.model.FlightReservationTestData;
import com.jaga.util.ConfigUtil;
import com.jaga.util.Constants;
import com.jaga.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends AbstractTest {

    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters({"testDataPath"})
    public void setParameters(String path) {
        this.testData = JsonUtil.getTestData(path, FlightReservationTestData.class);
    }

    @Test
    public void userRegistrationTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(ConfigUtil.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserDetails(testData.userRegistration().firstName(), testData.userRegistration().lastName());
        registrationPage.enterUserCredentials(testData.userRegistration().email(), testData.userRegistration().password());
        registrationPage.enterUserAddress(testData.userRegistration().address().street(),
                testData.userRegistration().address().city(), testData.userRegistration().address().state(),
                testData.userRegistration().address().zipCode());
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        registrationConfirmationPage.flightSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightsSearchTest() {
        FlightsSearchPage flightsSearchPage = new FlightsSearchPage(driver);
        Assert.assertTrue(flightsSearchPage.isAt());
        flightsSearchPage.selectPassengers(testData.parameters().noOfPassengers());
        flightsSearchPage.selectDepartureAndArrival(testData.flightSearch().departure(), testData.flightSearch().arrival());
        flightsSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightsSearchTest")
    public void flightSelectTest() {
        FlightSelectionPage flightSelectionPage = new FlightSelectionPage(driver);
        Assert.assertTrue(flightSelectionPage.isAt());
        flightSelectionPage.selectFlights();
        flightSelectionPage.confirmFlights();
    }

    @Test(dependsOnMethods = "flightSelectTest")
    public void flightConfirmationTest() {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        String flightPrice = flightConfirmationPage.getFlightDetails();
        Assert.assertEquals(flightPrice, testData.parameters().expectedPrice());
    }

}

