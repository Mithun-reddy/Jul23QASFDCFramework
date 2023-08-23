package demo;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Selenium {
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.get("https://login.salesforce.com/");
		
		WebElement email = driver.findElement(By.id("username"));
		
		email.sendKeys("jul22.qa@ta.com");
		
		driver.findElement(By.id("password")).sendKeys("Mithun123");
		
		driver.findElement(By.id("Login")).click();
		
		
		String expected = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("error"))));
		
		WebElement errorText;
		if(driver.findElement(By.id("error")).isDisplayed()) {
		
			errorText = driver.findElement(By.id("error"));
			
			String actual = errorText.getText();
		}
		
		if(driver.findElement(By.id("error")).isDisplayed()) {
			
			errorText = driver.findElement(By.id("error"));
			
			String actual = errorText.getText();
		}
		
if(driver.findElement(By.id("error")).isDisplayed()) {
			
			errorText = driver.findElement(By.id("error"));
			
			String actual = errorText.getText();
		}
		
	}
	
	

}
