package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nyka {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		Actions builder = new Actions(driver);
		WebElement brand = driver.findElement(By.xpath("//div[@id='brand_arrowUp']/preceding-sibling::a"));
		builder.moveToElement(brand).perform();
		WebElement brandSearchBox = driver.findElement(By.xpath("//input[@id='brandSearchBox']"));
		builder.moveToElement(brandSearchBox).click().sendKeys("L'Oreal Paris").perform();
		driver.findElement(By.xpath("//div[@id='list_L']/following-sibling::div/a[1]")).click();
		String title = driver.getTitle();
		if (title.contains("L'Oreal Paris")) {
			System.out.println("Title of the page is contains loreal paris ");
		} else {
			System.out.println("Title doesn't contain loreal paris ");
		}
		//driver.findElement(By.xpath("//div[@class='close']")).click();
		driver.findElement(By.xpath("//span[@title='POPULARITY']")).click();
		WebElement radioBtn = driver
				.findElement(By.xpath("//span[text()='customer top rated']/following-sibling::div"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(radioBtn));
		radioBtn.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//div[@class='clearfix'])[2]")).click();
		driver.findElement(By.xpath("//li[contains(@class,'filter-list-header')]")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']/following::div")).click();
		driver.findElement(By.xpath("//div[text()='Concern']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Color Protection']/following::div")).click();
		Thread.sleep(1000);
		//System.out.println(driver.findElement(By.xpath("(//div[@class='control__indicator'])[1]")).isSelected());
		List<WebElement> filterElements = driver.findElements(By.xpath("//ul[contains(@class,'applied-filter-lists')]/li"));
		for (WebElement webElement : filterElements) {
			if(webElement.getText().contains("Shampoo")) {
				System.out.println("Filter contains shampoo");
				break;
			}
		}
		driver.findElement(By.xpath("//img[contains(@alt,'Colour Protect')]")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> winList=new ArrayList<String>(windowHandles);
		driver.switchTo().window(winList.get(1));
		//driver.findElement(By.xpath("//span[text()='360ml']")).click();
		//Thread.sleep(1000);
		//driver.findElement(By.xpath("//span[text()='704']")).click();
		Thread.sleep(1000);
		String MRP = driver.findElement(By.xpath("//span[@class='mrp-tag']/following::span[2]")).getText();
		System.out.println("MRP of the shampoo is: "+MRP);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		//driver.findElement(By.xpath("//div[@class='close']")).click();
		driver.findElement(By.xpath("//div[@class='pull-left']//button")).click();
		System.out.println(driver.findElement(By.xpath("//div[@class='pull-left']//button")).getText());
		Thread.sleep(1000);
		//WebDriverWait wt=new WebDriverWait(driver, Duration.ofSeconds(2000));
		//wt.until(ExpectedConditions.textToBe(By.xpath("//div[@class='pull-left']//button"),  "ADDING TO BAG"));
		driver.findElement(By.xpath("//div[@class='AddToBagbox']")).click();
		Thread.sleep(1000);
		String GrandTotal1=driver.findElement(By.xpath("//span[text()='Grand Total']/following::div")).getText();
		System.out.println("GrandTotal1 is"+GrandTotal1);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='second-col']/button/span")).click();
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		String GrandTotal2=driver.findElement(By.xpath("(//div[@class='value'])[2]/span")).getText();
		System.out.println("GrandTotal2 is"+GrandTotal2);
	}

}
