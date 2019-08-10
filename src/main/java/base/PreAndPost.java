package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import dataReader.ExcelProvider;


public class PreAndPost extends WdEventImpl{

	protected String browserName;
	protected String dataSheetName;
	protected String dataExcelName;
	protected String dataSource;
	protected String fileName;
	protected String sqlStatement;
	private WebDriver driver;
	private WdEventImpl webDrvrEvntImpl;
	public static Properties config;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, IOException{
		config = new Properties();
		config.load(new FileInputStream(new File("./src/main/resources/config.properties")));
		startResult();
	}

	@BeforeMethod
	public synchronized void beforeMethod(){
		
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setBrowserName(browserName);
		dc.setPlatform(Platform.MAC);
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.DRIVER, Level.ALL);
		dc.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		
		// this is for grid run
		if(config.getProperty("RemoteExecution").equalsIgnoreCase("true"))
			try {
				driver = new RemoteWebDriver(new URL("http://"+config.getProperty("HubIP")+":"+config.getProperty("HPort")+"/wd/hub"), dc);
			} catch (MalformedURLException e) {
				reportStep("The hub at the ip address: "+config.getProperty("HubIP")+" is not available.", "FAIL");
			}
		else{ // this is for local run
			if(browserName.equalsIgnoreCase("chrome")){
				ChromeDriverService service = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File("./drivers/chromedriver"))
                        .usingAnyFreePort()
                        .build();
				ChromeOptions options = new ChromeOptions();
				options.merge(dc); 
				 driver = new ChromeDriver(service, options);
			}else{
				GeckoDriverService service = new GeckoDriverService.Builder()
                        .usingDriverExecutable(new File("./drivers/gecko"))
                        .usingAnyFreePort()
                        .build();
				FirefoxOptions options = new FirefoxOptions();
				options.merge(dc); 
				driver = new FirefoxDriver(service, options);
			}
		}

		webDrvrEvntImpl  = new WdEventImpl();
		webDrvrEvntImpl.driver = driver;
		webDrvrEvntImpl.e_driver = init(driver);
		setDriver(webDrvrEvntImpl);
		startTestCase(testCaseName, testDescription, category, authors);
	}

	@BeforeMethod
	public void launchApp() {
		getEventDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		getEventDriver().get(config.getProperty("URL"));	
	}

	@AfterSuite
	public void afterSuite(){
		endResult();
	}


	@AfterMethod
	public void afterMethod(){
		getDriver().quit();
		endTestcase();	
		unset(); 
	}

	@DataProvider(name="fetchData",parallel=false)
	public  Object[][] getData(){
			return ExcelProvider.getDataFromExcel(dataExcelName,dataSheetName);

	}	


}
