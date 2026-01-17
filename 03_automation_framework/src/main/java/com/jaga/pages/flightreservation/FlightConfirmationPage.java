package com.jaga.pages.flightreservation;

import com.jaga.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmationPage extends AbstractPage {


    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);

    @FindBy(css = "#flights-confirmation-section  .card-body .row:nth-child(1) .col:nth-child(2)")
    private WebElement flightConfirmationNumber;

    @FindBy(css = "#flights-confirmation-section  .card-body .row:nth-child(3) .col:nth-child(2)")
    private WebElement flightTotalPrice;


    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.flightConfirmationNumber));
        return this.flightConfirmationNumber.isDisplayed();
    }

    public String getFlightDetails() {
        String confirmationNumber = this.flightConfirmationNumber.getText();
        String price = this.flightTotalPrice.getText();
        log.info("Flight Confirmation: {}", confirmationNumber);
        log.info("Flight Price: {}", price);
        return price;
    }

}
