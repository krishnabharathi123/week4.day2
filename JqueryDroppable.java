package week4.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JqueryDroppable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/droppable/");
		driver.manage().window().maximize();
		driver.switchTo().frame(0);
		Actions builder = new Actions(driver);
		WebElement drag = driver.findElement(By.id("draggable"));
		WebElement drop = driver.findElement(By.id("droppable"));
		// Method1
		builder.dragAndDrop(drag, drop).perform();
		// Method2
		/*
		 * Point location = drop.getLocation(); int xOff=location.getX(); int
		 * yOff=location.getY(); builder.dragAndDropBy(drag, xOff, yOff).perform();
		 */
		// Method3
		// builder.clickAndHold(drag).moveToElement(drag, xOff, yOff).perform();

	}

}
