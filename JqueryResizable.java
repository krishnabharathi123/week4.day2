package week4.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JqueryResizable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/resizable/");
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		driver.switchTo().frame(0);
		WebElement resiableEle = driver.findElement(By.xpath("//div[@id='resizable']/div[3]"));
		
		boolean flag=true;
		//JavascriptExecutor js=
		
		while(flag) {
			int i=50;
		Actions builder = new Actions(driver);
		builder.clickAndHold(resiableEle).perform();
		builder.moveToElement(resiableEle, i, 50).perform();
		String styleVal=driver.findElement(By.id("resizable")).getAttribute("style");
		if(styleVal.contains("35")) {
			System.out.println(styleVal);
			flag=false;
		}
		i++;
		}

	}

}
