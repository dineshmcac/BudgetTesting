package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ReviewAndBook extends AbstractPage {
	
	public ReviewAndBook(){
		PageFactory.initElements(getEventDriver(), this);
	}

	@FindBy(how = How.ID, using = "firstname")
	WebElement firstName;
	
	@FindBy(how = How.ID, using = "lastname")
	WebElement lastName;
	
	@FindBy(how = How.ID, using = "email")
	WebElement email;
	
	public ReviewAndBook fillDetails(String fname, String lname, String e_mail) {
		type(firstName, fname);
		type(lastName, lname);
		type(email, e_mail);
		return new ReviewAndBook();
	}
}
