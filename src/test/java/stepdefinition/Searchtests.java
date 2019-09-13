


package stepdefinition;





import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import data.LoadDataProperties;
import pages.SearchPage;
import testcases.TestBases;



public class Searchtests extends TestBases {
	SearchPage searchobject;
	String carsearchname = LoadDataProperties.userdata.getProperty("carsearchname");
	String hreff = LoadDataProperties.userdata.getProperty("href");
	@Given("^the user writing on the searchtextfield$")
	public void the_user_writing_on_the_searchtextfield()  {

		System.out.println("driver"+driver);

		searchobject = new SearchPage(driver);
		searchobject.userWritingOnSearchTxt(carsearchname);
		searchobject.clickOnTheSearchButton();;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@When("^click on the cars and redirect to next page$")
	public void click_on_the_cars_and_redirect_to_next_page() {
		System.out.println("driver"+driver);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(searchobject.carstxt));
		searchobject=new SearchPage(driver);
		searchobject.clickOnTheCarsText();	
		try {			
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	@When("^click on third car$")
	public void click_on_third_car()   {
		System.out.println("driver"+driver);

		WebElement webtable = driver.findElement(By.id("searchResultsTable"));
		List<WebElement> rows = webtable.findElements(By.tagName("tr"));
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(rows.get(3)));	
		rows.get(3).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@When("^TakeimageScreenshootofcarwithprice$")
	public void takeimagescreenshootofcarwithprice() throws IOException {
		new WebDriverWait(driver, 20).until(ExpectedConditions.titleContains("Renault"));
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("./ScreenShots/imageofcarwithprice.png"));

	}

	@When("^SaveWebPage \\(\\)$")
	public void savewebpage() throws AWTException {
		Robot robot = new Robot();
		String href = hreff;
		StringSelection selection = new StringSelection(href);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, null);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(3000);
	}
	@Then("^assert Of The Price$")
	public void assert_Of_The_Price() { 
		searchobject=new SearchPage(driver);
		List<WebElement> salary = driver.findElements(By.tagName("h3"))	;
		Iterator<WebElement> i = salary.iterator();
		while(i.hasNext()) {
			WebElement salary1 = i.next();
			if (salary1.getText().contains("31,750 TL")) {
				System.out.println("true");
				break;
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}