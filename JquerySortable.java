package week4.day2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JquerySortable {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/sortable/");
		driver.manage().window().maximize();
		for(int j=1;j<=5;j++) {
			Thread.sleep(1000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		driver.switchTo().frame(0);
		WebElement sort = driver.findElement(By.id("sortable"));
		List<WebElement> sortList = sort.findElements(By.xpath("//li/span"));
		Point lastLoc = sortList.get(6).getLocation();
		int xOff = lastLoc.getX();
		int yOff = lastLoc.getY();
		/*
		 * WebElement element1=sortList.get(0);
		 * 
		 * WebElement element2=sortList.get(6); Actions builder=new Actions(driver);
		 * builder.clickAndHold(element1).moveToElement(element2).release().perform();
		 */
		for (int i = 0; i < sortList.size(); i++) {
			Thread.sleep(1000);
			WebElement element1=sortList.get(3);
			
			WebElement element2=sortList.get(sortList.size()-1);
			Actions builder=new Actions(driver);
			//builder.clickAndHold(element1).dragAndDrop(element1, element2).perform();
			//builder.clickAndHold(element).moveByOffset(xOff, yOff).perform();
			//builder.dragAndDropBy(element1, xOff, yOff).perform();
			builder.clickAndHold(element1).moveToElement(element2).release().perform();
		}

	}

}
