package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.PreAndPost;
import pages.HomePage;

public class BuyLowPriceSUV extends PreAndPost{
	
	@BeforeClass
	public void setValues() {
		browserName="chrome";
		testCaseName="Buy SUV";
		testDescription="Buy SUV with low price";
		category="smoke";
		dataSource="Excel";
		dataExcelName="TC001";
		dataSheetName="Sheet3";
		authors="Dinesh";
	}
	
	@Test(dataProvider="fetchData")
	public void BuyCar(String PickUpLocation, 
			String fname,
			String lname,
			String e_mail)
	{
		new HomePage()
		.selectCar(PickUpLocation)
		.selectVehicles()
		.fillDetails(fname, lname, e_mail);
	}

}
