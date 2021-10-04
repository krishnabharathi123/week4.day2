package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro",Keys.ENTER);
		Thread.sleep(1000);
		String productPrice=driver.findElement(By.className("a-price-whole")).getText();
		System.out.println("price of the first product is: "+productPrice);
		String noOfCusmrRatings=driver.findElement(By.xpath("//div[contains(@class,'a-size-small')]/span[2]")).getText();
		System.out.println("No of customer rating is: "+noOfCusmrRatings);
		Actions builder=new Actions(driver);
		WebElement star=driver.findElement(By.xpath("//div[contains(@class,'a-size-small')]//span"));
		//builder.moveToElement(star).perform();
		star.click();
		String fiveStarPrcnt=driver.findElement(By.xpath("(//a[contains(@title,'reviews have 5 stars')])[3]")).getText();
		System.out.println("Five start percentage is: "+fiveStarPrcnt);
		driver.findElement(By.xpath("//h2[contains(@class,'a-size-mini')]")).click();
		Set<String> windowId=driver.getWindowHandles();
		List<String> winList=new ArrayList<String>(windowId);
		driver.switchTo().window(winList.get(1));
		WebElement phImg=driver.findElement(By.xpath("//div[@id='imgTagWrapperId']/img"));
		File scr=phImg.getScreenshotAs(OutputType.FILE);
		File dest=new File("./Snaps/Amazon.png");
		FileUtils.copyFile(scr, dest);
		Thread.sleep(1000);
		driver.findElement(By.id("add-to-cart-button")).click();
		//WebDriverWait wt=new WebDriverWait(driver,Duration.ofSeconds(20));
		//wt.until(ExpectedConditions.textToBePresentInElement((WebElement) By.id("attach-accessory-cart-subtotal"), productPrice));
		Thread.sleep(3000);
		String subTotal=driver.findElement(By.id("attach-accessory-cart-subtotal")).getText();
		System.out.println("Cart subtotal is: "+subTotal);
		if(subTotal.contains(productPrice)) {
			System.out.println("sub total is equal to product price");
		}
		else {
			System.out.println("sub total not equals to product price");
		}
	}

}
