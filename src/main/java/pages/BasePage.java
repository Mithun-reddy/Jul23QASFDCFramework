package pages;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	public static Logger logger = LogManager.getLogger("BASETEST");
	protected WebDriver driver;
	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getPageTitle() {
	//	myLog.info("page tittle is returned");
		return driver.getTitle();
	}

	public void refreshPage() {
		driver.navigate().refresh();
	//	myLog.info("page is refreshed");

	}

	public String getTextFromElement(WebElement ele, String objectName) {
		String data = ele.getText();
		//myLog.info("extracted the text from" + objectName);
		return data;
	}

	

	public void enterText(WebElement ele, String data, String objectName) {
		waitForVisibility(ele,30,objectName);
		if (ele.isDisplayed()) {
			clearElement(ele, objectName);
			ele.sendKeys(data);
			System.out.println("Pass:"+objectName+" is entered to the username filed");
		} else {
			System.out.println(objectName + " element is not displayed");
		}
	}

	public void clearElement(WebElement ele, String ObjectName) {
		if (ele.isDisplayed()) {
			ele.clear();
			System.out.println(ObjectName + " is cleared");
		} else {
			System.out.println(ObjectName + " element is not displayed");
		}
	}

	public void clickElement(WebElement ele, String objectName) {
		if (ele.isEnabled()) {
			ele.click();
			System.out.println(objectName + "button is clicked");
			
		} else {
			System.out.println("button element is not enabled");
			
		}
	}

	// ****************8handling alerts reusable methods*************************

	public Alert switchToAlert() {

		Alert alert = driver.switchTo().alert();
		System.out.println("switched to alert");
		return alert;
	}

	public void AcceptAlert(Alert alert) {

		System.out.println("Alert accepted");
		alert.accept();

	}

	public String getAlertText(Alert alert, String objectname) {
		System.out.println("etracting text in the " + objectname + "alert");
		String text = alert.getText();
		System.out.println("text is extracted from alert box is==" + text);
		return text;

	}

	public void dismisAlert() {

		Alert alert = switchToAlert();
		alert.dismiss();
		System.out.println("Alert dismissed");

	}
	// ******************************alert
	// ends**************************************

	// ******************************Action class reusable methods
	// **************************************
	public void waitUntilPageLoads() {
		System.out.println("waiting until page loads with 30 sec maximum");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}

	public void moveTOElementAction(WebElement ele, String objName) {
		Actions action = new Actions(driver);
		action.moveToElement(ele).build().perform();
		System.out.println(" cursor moved to web element " + objName);
	}

	public void ContextClickAction(WebElement ele, String objName) {
		Actions action = new Actions(driver);
		action.contextClick(ele).build().perform();
		System.out.println("right click persormed on web element " + objName);
	}

	// ******************************Action class reusable method
	// ends**************************************

	// ******************************Select class reusable method
	// starts*************************************

	public void selectByTextData(WebElement element, String text, String objName) {
		Select selectCity = new Select(element);
		selectCity.selectByVisibleText(text);
		System.out.println(objName + " selected " + text);

	}

	public void selectByIndexData(WebElement element, int index, String objName) {
		waitForVisibility(element, 5, objName);
		Select selectCity = new Select(element);
		selectCity.selectByIndex(index);
		System.out.println(objName + " selected with index=" + index);

	}

	public void selectByValueData(WebElement element, String text, String objName) {
		Select selectCity = new Select(element);
		selectCity.selectByValue(text);
		System.out.println(objName + " selected ");
	}

	// ******************************select class reusable method
	// ends**************************************

	public void waitForVisibility(WebElement ele, int time, int pollingtime, String objectName) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(time)).pollingEvery(Duration.ofSeconds(pollingtime))
				.ignoring(ElementNotInteractableException.class);

		wait.until(ExpectedConditions.visibilityOf(ele));
		System.out.println(objectName + " is waited for visibility using fluent wait");
	}

	public void WaitUntilPresenceOfElementLocatedBy(By locator, String objName) {
		System.out.println("waiting for an web element " + objName + " for its visibility");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public void waitUntilElementToBeClickable(By locator, String objName) {
		System.out.println("waiting for an web element " + objName + " to be clickable");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitForVisibility(WebElement ele, int time, String objectNam) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitForAlertPresent(int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void switchToWindowOpned(String mainWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			if (!mainWindowHandle.equalsIgnoreCase(handle))
				driver.switchTo().window(handle);
		}
		System.out.println("switched to new window");
	}

	public WebElement selectFromListUsingText(List<WebElement> list, String text) {
		WebElement element = null;
		for (WebElement i : list) {
			if (i.getText().equalsIgnoreCase(text)) {
				System.out.println("selected=" + i.getText());
				element = i;
				break;
			}

		}
		return element;

	}

}
