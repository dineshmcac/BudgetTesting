package base;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WdMethods extends WdEventImpl{	

	public WebElement locateElement(String how, String using) {
		WebElement ele = null;
		switch(how) {
		case("id"):
			ele = getEventDriver().findElement(By.id(using));
		break;
		case("name"):
			ele = getEventDriver().findElement(By.name(using));
		break;
		case("linkText"):
			ele = getEventDriver().findElement(By.linkText(using));
		break;
		case("xpath"):
			ele = getEventDriver().findElement(By.xpath(using));
		break;
		case("partialLinkText"):
			ele = getEventDriver().findElement(By.partialLinkText(using));
		break;
		case("cssSelector"):
			ele = getEventDriver().findElement(By.cssSelector(using));
		break;
		case("tagName"):
			ele = getEventDriver().findElement(By.tagName(using));
		break;
		case("className"):
			ele = getEventDriver().findElement(By.className(using));
		break;
		default:
			reportStep("The given locator "+how+" is not correct", "FAIL");
			break;
		}
		return ele;			
	}

	public void clear(WebElement ele) {
		ele.clear();
	}

	public void type(WebElement ele, String data) {
		ele.sendKeys(data);
	}

	public void click(WebElement ele) {
		ele.click();
	}

	public void selectUsingText(WebElement ele, String text) {
		select(ele, "text", text);
	}

	public void selectUsingValue(WebElement ele, String value) {
		select(ele, "value", value);
	}

	public void selectUsingIndex(WebElement ele, int index) {
		new Select(ele).selectByIndex(index);
	}

	private void select(WebElement ele, String type, String textOrValue) {
		if(type.equalsIgnoreCase("text"))
			new Select(ele).selectByVisibleText(textOrValue);
		else
			new Select(ele).selectByValue(textOrValue);
	}

	public String getText(WebElement ele){
		return ele.getText();
	}	

	public String getAttributeText(WebElement ele, String value){
		return ele.getAttribute(value);
	}

	
}
