package week4.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JqueryDraggable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/draggable/");
		driver.manage().window().maximize();
		driver.switchTo().frame(0);
		Actions builder = new Actions(driver);
		WebElement draggableEle = driver.findElement(By.id("draggable"));
		// builder.clickAndHold(draggableEle).moveByOffset(33, -17).perform();
		System.out.println(draggableEle.getLocation());
		for (int i = 1; i <= 100; i = i + 2) {
			builder.clickAndHold(draggableEle).moveByOffset(i, 10).perform();
			String style = driver.findElement(By.id("draggable")).getAttribute("style");
			if (style.contains("7")) {
				System.out.println(draggableEle.getLocation());
				break;
			}
		}

	}

}
