package com.jaga.pages.flightreservation;

import com.jaga.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightsSearchPage extends AbstractPage {

    @FindBy(id = "passengers")
    private WebElement passengerSelect;

    @FindBy(id = "depart-from")
    private WebElement departFromSelect;

    @FindBy(id = "arrive-in")
    private WebElement arrivalInSelect;

    @FindBy(id = "search-flights")
    private WebElement searchFlightsButton;

    public FlightsSearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.passengerSelect));
        return this.passengerSelect.isDisplayed();
    }

    public void selectPassengers(String noOfPassengers) {
        Select passengers = new Select(this.passengerSelect);
        passengers.selectByValue(noOfPassengers);
    }

    public void selectDepartureAndArrival(String depart, String arrival) {
        Select departFrom = new Select(this.departFromSelect);
        departFrom.selectByVisibleText(depart);
        Select arrivalIn = new Select(this.arrivalInSelect);
        arrivalIn.selectByVisibleText(arrival);
    }

    public void searchFlights() {
        this.searchFlightsButton.click();
    }

}
