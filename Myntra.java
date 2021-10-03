package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {
	int sum = 0;

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notification");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.myntra.com/");
		Actions builder = new Actions(driver);
		WebElement Men = driver.findElement(By.xpath("//a[text()='Men']"));
		builder.moveToElement(Men).perform();
		driver.findElement(By.xpath("//li/a[text()='Jackets']")).click();
		String itemsCount = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		Thread.sleep(1000);
		System.out.println("Jackets for Men count is: " + itemsCount);
		List<WebElement> categories = driver.findElements(By.xpath("//ul[@class='categories-list']//span"));
		Myntra my = new Myntra();
		for (int i = 0; i < categories.size(); i++) {
			String category = categories.get(i).getText();
			String catValues = category.replaceAll("\\D", "");
			my.sum = my.sum + Integer.parseInt(catValues);
		}
		System.out.println("Sum of categoriesis :" + my.sum);
		String stringSum = String.valueOf(my.sum);
		if (itemsCount.contains(stringSum)) {
			System.out.println("sum of categories matches with the jacket count");
		} else {
			System.out.println("Sum of category doesn't matches with the jacket count");
		}
		driver.findElement(By.xpath("//ul[@class='categories-list']//li")).click();
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search brand']")).sendKeys("Duke");
		driver.findElement(By.xpath("//input[@value='Duke']/following::div")).click();
		driver.findElement(By.xpath("//span[contains(@class,'sprites-remove')]")).click();
		Thread.sleep(1000);
		List<WebElement> brandNms = driver.findElements(By.xpath("//div[@class='product-productMetaInfo']/h3"));
		List<String> brandList = new ArrayList<String>();
		Thread.sleep(1000);
		for (WebElement brands : brandNms) {

			brandList.add(brands.getText());
		}
		System.out.println(brandList);
		System.out.println(brandList.size());
		int count = 0;
		for (int i = 0; i < brandList.size(); i++) {
			if (!brandList.get(i).equals("Duke")) {
				System.out.println("Product number is: " + i + " name is: " + brandList.get(i) + " which is not Duke");
				break;
			} else {
				count++;
				// System.out.println(count);
				if (count == brandList.size()) {
					System.out.println("all the products are Duke");
				}
			}
		}
		driver.findElement(By.xpath("//span[text()='Recommended']")).click();
		driver.findElement(By.xpath("//ul[@class='sort-list']/li[3]")).click();
		WebElement fProduct = driver.findElement(By.xpath("//span[@class='product-discountedPrice']"));
		System.out.println("First product price is " + fProduct.getText());
		fProduct.click();
		Thread.sleep(1000);
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(winList.get(1));
		List<WebElement> productPic = driver.findElements(By.xpath("//div[@class='image-grid-imageContainer']"));
		System.out.println("No of imgs are: " + productPic.size());
		for (int j = 0; j < productPic.size(); j++) {
			File src = productPic.get(j).getScreenshotAs(OutputType.FILE);
			File dest = new File("./Snaps/MyntrPic" + j + ".png");
			FileUtils.copyFile(src, dest);
		}

		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-350)", "");
		driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();
		driver.quit();
	}

}
