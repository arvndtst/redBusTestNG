package in.RedBus;

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



public class BookingBus {
	static WebDriver driver;

	@BeforeClass
	public static void launchBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.redbus.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	

	@Parameters({"source"})
	@Test(priority =0)
	public void enterFrom(String data) {
		WebElement from = driver.findElement(By.xpath("//div[text()='From']"));
		from.click();
		WebElement fromIn=driver.findElement(By.xpath("//input[@id='srcDest']"));
		fromIn.sendKeys(data);
		WebElement bp = driver.findElement(By.xpath("//div[@aria-label='Tambaram, Chennai']"));
		bp.click();


	}

	@Test(priority =1)
	public void enterTo() {
		WebElement toin=driver.findElement(By.xpath("//input[@id='srcDest']"));
		toin.sendKeys("Trichy");
		WebElement dp = driver.findElement(By.xpath("//div[text()='Trichy']"));
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
	public void toggleWomensonly() {
		WebElement toggle = driver.findElement(By.xpath("//input[@id='switch']"));
		toggle.click();
		try {
			driver.findElement(By.xpath("//button[@aria-label=\"Close\"]")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(priority =4)
	public void searchBuses() {
		WebElement search = driver.findElement(By.xpath("//button[text()='Search buses']"));
		search.click();
	}

	@Test(priority =5)
	public void busTitle() {
		List<WebElement> buses = driver.findElements(By.xpath("//div[@class=\"travelsName___950ec8\"]"));
		for (WebElement bus : buses) {
			System.out.println(bus.getText());
		}
	}

	@AfterClass
	public static void quitBrowser() {
		driver.quit();

	}
}
