package week4.day2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JquerySelectable {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/selectable/");
		driver.manage().window().maximize();
		driver.switchTo().frame(0);
		WebElement element = driver.findElement(By.id("selectable"));
		List<WebElement> selectableEle = element.findElements(By.tagName("li"));
		int eleSize = selectableEle.size();
		for (int i = 0; i <= eleSize-1; i++) {
			Thread.sleep(1000);
			/*
			 * if (i == 4) { continue; }//this alone will be skipped
			 */
			Actions builder = new Actions(driver);
			WebElement webElement = selectableEle.get(i);
			//webElement.click();//this will scroll down and click all elements
			//webElement.sendKeys(Keys.CONTROL);//ElementNotInteractable
			//builder.moveToElement(webElement).keyDown(Keys.CONTROL).click().perform();//For this code arrow down will not wrk even manually 
			builder.moveToElement(webElement).click().perform();
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);//for actions class scroll down to click the elements not visible in main page.

		}

	}

}
