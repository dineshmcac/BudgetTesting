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
	
	@FindBy(how = How.ID, using = "to")
	WebElement toDate;
	
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
	
	
	public Vehicles selectCar(String PickUpLocation,
			String StartDateTime,
			String EndDateTime,
			String DropDate_from
			)  {
		type(pickUpElement, PickUpLocation);
		click(auto_complete);
		click(fromDate);
		click(currentDate);
		selectUsingValue(selectTime, StartDateTime);
		click(toDate);
		selectDropDate(DropDate_from);
		selectUsingValue(selectTime, EndDateTime);
		click(selectCar);
		return new Vehicles();
	}
	
	void selectDropDate(String DropDate_from) {
		int date = Integer.parseInt(currentDate.getText());
		int month = Integer.parseInt(currentDate.getAttribute("data-month"));
		int year = Integer.parseInt(currentDate.getAttribute("data-year"));

		LocalDate date1 =  LocalDate.of(year, month, date).plusDays(Integer.parseInt(DropDate_from));
		
		String enddate = "//td[@data-month='"+date1.getMonthValue()+"' and @data-year='"+date1.getYear()+"']/a[text()="+date1.getDayOfMonth()+"]";
		ele = getDriver().findElement(By.xpath(enddate));
		ele.click();
	}


}