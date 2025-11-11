package in.RedBus;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;


import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class BookingTrain {
	static WebDriver driver;

	@BeforeClass
	public static void launchBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.redbus.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//span[text()='Train tickets']")).click();
	}
	

	@Parameters({"source"})
	@Test(priority =0)
	public void enterFrom(String data) {
		WebElement from = driver.findElement(By.xpath("//div[text()='From']"));
		from.click();
		WebElement fromIn=driver.findElement(By.xpath("//label[text()='From']/preceding::input"));
		fromIn.sendKeys(data);
		WebElement bp = driver.findElement(By.xpath("//div[text()='Tambaram']"));
		bp.click();


	}

	@Parameters({"destination"})
	@Test(priority =1)
	public void enterTo(String data) {
		WebElement toin=driver.findElement(By.xpath("//label[text()='To']/preceding::input"));
		toin.sendKeys(data);
		WebElement dp = driver.findElement(By.xpath("//div[text()='Tiruchchirapali']"));
		dp.click();


	}

	@Test(priority =2)
	public void enterDate() {
		WebElement cal = driver.findElement(By.xpath("//span[text()='Date of Journey']"));
		cal.click();
		WebElement date = driver.findElement(By.xpath("//span[text()='15']"));
		date.click();

	}

	@Test(priority =3)
	public void toggleFreeCancellation() {
		WebElement toggle = driver.findElement(By.xpath("//input[@id='swtch']"));
		toggle.click();

	}

	@Test(priority =4)
		public void searchTrains() {
		WebElement search = driver.findElement(By.xpath("//button[text()='Search Trains']"));
		search.click();
	}

	@Test(priority =5)
	public void trainName() {
		List<WebElement> trains = driver.findElements(By.xpath("//span[@class='trainName___7d75f3']"));
		for (WebElement train : trains) {
			System.out.println(train.getText());
		}
	}

	@AfterClass
	public static void quitBrowser() {
		driver.quit();

	}
}
