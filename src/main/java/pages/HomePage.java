package pages;

import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage{
	
	public HomePage(){
		PageFactory.initElements(getEventDriver(), this);
	}
	
	@FindBy(how = How.ID, using = "PicLoc_value")
	WebElement pickUpElement;
	
	@FindBy(how = How.ID, using = "from")
	WebElement fromDate;
	
	@FindBy(how = How.XPATH, using = "//a[contains(@class,'ui-datepicker-startdate')]/..")
	WebElement currentDate;

	//@FindBy(how = How.XPATH, using = "//td[@data-month='7' and @data-year='2019']/a[text()=16]")
	WebElement ele;
	
	@FindBy(how = How.XPATH, using = "//select[@name='reservationModel.pickUpTime']")
	WebElement selectTime;
	
	@FindBy(how = How.ID, using = "res-home-select-car")
	WebElement selectCar;
	
	@FindBy(how = How.XPATH, using = "//div[@class='angucomplete-description']")
	WebElement auto_complete;
	
	
	
	//price[@ng-if='checkVisible() && !isLmbRequest']
	
	//extras
	//span[text()='Base Rate']/../span[2]
	//button[text()='Continue']
	
	//review-and-book
	//firstname
	//lastname
	//email
	
	
	public Vehicles selectCar(String PickUpLocation)  {
		type(pickUpElement, PickUpLocation);
		click(auto_complete);
		click(fromDate);

		int date = Integer.parseInt(currentDate.getText());
		int month = Integer.parseInt(currentDate.getAttribute("data-month"));
		int year = Integer.parseInt(currentDate.getAttribute("data-year"));
		
		LocalDate date1 =  LocalDate.of(year, month, date).plusDays(5);
		String enddate = "//td[@data-month='"+date1.getMonthValue()+"' and @data-year='"+date1.getYear()+"']/a[text()="+date1.getDayOfMonth()+"]";
		ele = getDriver().findElement(By.xpath(enddate));
		ele.click();
		selectUsingValue(selectTime, "string:1:00 AM");
		click(selectCar);
		return new Vehicles();
	}


}