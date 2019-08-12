package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Vehicles extends AbstractPage {
	public Vehicles(){
		PageFactory.initElements(getEventDriver(), this);
	}
	
	@FindBy(how = How.ID, using = "res-vehicles-sort")
	WebElement vehical_filter;
	
	@FindBy(how = How.XPATH, using = "//ul[@class='dropdown-menu']/li")
	WebElement lowPrice;
	
	@FindBy(how = How.ID, using = "res-vehicles-filter-by-vehicle-type")
	WebElement vehical_type;
	
	@FindBy(how = How.XPATH, using = "//li[@vehiclecode='suv']")
	WebElement SUV;
	
	@FindBy(how = How.ID, using = "res-vehicles-pay-now")
	WebElement pay_now;
	
	@FindBy(how = How.XPATH, using = "//price[@ng-if='checkVisible() && !isLmbRequest']")
	WebElement pricelist;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Base Rate']/../span[2]")
	WebElement actualPrice;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Continue']")
	WebElement Continue;

	
	public ReviewAndBook selectVehicles() {
		click(vehical_filter);
		click(lowPrice);
		click(vehical_type);
		click(SUV);
		String choosed = pricelist.getText().replaceAll("\n", "").trim();
		click(pay_now);
		Assert.assertEquals(choosed, actualPrice.getText().replaceAll("\n", "").trim());
		click(Continue);
		return new ReviewAndBook();
	}
}
