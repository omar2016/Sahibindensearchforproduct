package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import data.LoadDataProperties;


public class TestBases extends AbstractTestNGCucumberTests{
	
	public static  WebDriver driver;
	String baseurl = LoadDataProperties.userdata.getProperty("baseurl");
	
	@BeforeSuite
	public void startDriver () {
	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driver/chromedriver.exe");
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--incognito");
	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	if(driver==null) {
		driver=new ChromeDriver(capabilities);
	}
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	driver.navigate().to(baseurl);
	try {
		
		
		
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	}
	
	@AfterSuite
	public void stopDriver() 
	{
		if (driver !=null ) {
			driver.quit();
		}

	}
}


