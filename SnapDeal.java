package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.messages.internal.com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		driver.findElement(By.xpath("//span[@class='catText'][contains(text(),'Fashion')]")).click();
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		String items=driver.findElement(By.xpath("//h1[@category='Sports Shoes']/span")).getText();
		System.out.println("Sports shoes count: "+items);
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		driver.findElement(By.xpath("//div[@class='sort-drop clearfix']")).click();
		driver.findElement(By.xpath("//ul[@class='sort-value']/li[2]")).click();
		Thread.sleep(1000);
		List<WebElement> shoes = driver.findElements(By.xpath("//div[@class='lfloat marR10']/span[2]"));
		List<Integer> shoeRate=new ArrayList<Integer>();
		System.out.println(shoes.size());
		for(int i=0;i<shoes.size();i++) {
			shoeRate.add(Integer.parseInt(shoes.get(i).getAttribute("data-price")));
		}
		//System.out.println(shoeRate);
		List<Integer> sortedShoeRate=new ArrayList<Integer>(shoeRate);
		Collections.sort(sortedShoeRate);
		//System.out.println(sortedShoeRate);
		if(shoeRate.equals(sortedShoeRate)) {
			System.out.println("items displayed are sorted correctly");
		}
		else {
			System.out.println("items displayed are not sorted correctly");
		}
		
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("window.scrollBy(0,350)", "");
		 
		  Thread.sleep(1000);
		WebElement minVal=driver.findElement(By.xpath("//div[@class='price-text-box']/input"));
		minVal.clear();
		minVal.sendKeys("900");
		WebElement maxVal=driver.findElement(By.xpath("(//div[@class='price-text-box']/input)[2]"));
		maxVal.clear();
		maxVal.sendKeys("1200");
		driver.findElement(By.xpath("//div[contains(@class,'btn-theme-secondary')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//label[@for='Color_s-Black']")).click();
		Thread.sleep(1000);
		System.out.println("***Filtered elements are****");
		List<WebElement> filters=driver.findElements(By.xpath("//div[@class='filters-top-selected']/div//a"));
		for (WebElement webElement : filters) {
			System.out.println(webElement.getText());
		}
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("//picture[@class='picture-elem']"))).perform();
		driver.findElement(By.xpath("//div[@class='clearfix row-disc']/div")).click();
		System.out.println("Cost is: "+driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText());
		System.out.println("Discount percentage is: "+driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText());
		WebElement shoePic=driver.findElement(By.xpath("//img[@itemprop='image']"));
		FileUtils.copyFile(shoePic.getScreenshotAs(OutputType.FILE), new File("./Snaps/shoePic.png"));
		driver.findElement(By.xpath("//div[contains(@class,'marR10')]/i")).click();
		Thread.sleep(1000);
		driver.close();
	}

}
