package testcases;




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
import org.testng.annotations.Test;

import data.LoadDataProperties;
import pages.SearchPage;


public class Searchtestcases extends TestBases {
	SearchPage searchobject;
	String carsearchname = LoadDataProperties.userdata.getProperty("carsearchname");
	String hreff = LoadDataProperties.userdata.getProperty("href");
	@Test(priority=1)
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
	@Test(priority=2 , dependsOnMethods= {"the_user_writing_on_the_searchtextfield"})
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
	@Test(priority=3, dependsOnMethods= {"click_on_the_cars_and_redirect_to_next_page"})
	public void click_on_thi_rd_car()  {
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
	@Test(priority=4, dependsOnMethods= {"click_on_thi_rd_car"})
	public void TakeimageScreenshootofcarwithprice () throws IOException {
		new WebDriverWait(driver, 20).until(ExpectedConditions.titleContains("Renault"));
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("./ScreenShots/imageofcarwithprice.png"));

	}
	@Test(priority=5, dependsOnMethods= {"TakeimageScreenshootofcarwithprice"})
	public void SaveWebPage () throws IOException, AWTException {
		Robot robot = new Robot();
		String href =hreff;
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
				  try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    }


	@Test(priority=6)
	public void assertionOfThePrice () {
		searchobject=new SearchPage(driver);
		List<WebElement> salary = driver.findElements(By.tagName("h3"))	;
		Iterator<WebElement> i = salary.iterator();
		while(i.hasNext()) {
			WebElement salary1 = i.next();
			if (salary1.getText().contains(	searchobject.copySalary())) {
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
