package pages;



import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends PageBases {

	public  SearchPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(id="searchText")
	WebElement searchtxtfield ;

	@FindBy(xpath="//*[contains(@type,'submit')]")
	WebElement searchbtn ;


	@FindBy(linkText="Cars")
	public WebElement carstxt ;


	@FindBy(tagName="h3")
	public List<WebElement> salary;


	public void userWritingOnSearchTxt (String carname )  {	

		setTextElementText(searchtxtfield,carname) ;

	}
	public void clickOnTheSearchButton ( ) {
		clickButton(searchbtn);
	}

	public void clickOnTheCarsText() {
		clickButton(carstxt);
	}
	public String copySalary () {
	
		return salary.get(2).getText();
	}
	

}
