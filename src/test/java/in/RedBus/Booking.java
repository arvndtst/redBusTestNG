package in.RedBus;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class Booking {
	static SoftAssert soft=new SoftAssert();
	static WebDriver driver;

	@BeforeClass
	public static void launchBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.redbus.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	@BeforeMethod
	public void beforeMeth() {
		System.out.println("Before Method");
	}
	
	@AfterMethod
	public void afterMeth() {
		System.out.println("After Method");
	}
	
	@DataProvider(name="frm")
	public Object[][] getInstance1(){
		return new Object[][] {{"Chennai"}};
	}
	
	@DataProvider(name="to")
	public Object[][] getInstance2(){
		return new Object[][] {{"Trichy"}};
		
	}

	@Test(priority =0, dataProvider="frm")
	public void enterFrom(String data) {
		WebElement from = driver.findElement(By.xpath("//div[text()='From']"));
		Assert.assertTrue(from.isEnabled());
		from.click();
		WebElement fromIn=driver.findElement(By.xpath("//input[@id='srcDest']"));
		fromIn.sendKeys(data);
		WebElement bp = driver.findElement(By.xpath("//div[@aria-label='Vandalur, Chennai']"));
		bp.click();


	}

	@Test(priority =1, dependsOnMethods ="enterFrom",dataProvider="to")
	public void enterTo(String data) {
		WebElement toin=driver.findElement(By.xpath("//input[@id='srcDest']"));
		toin.sendKeys(data);
		WebElement dp = driver.findElement(By.xpath("//div[text()='Trichy']"));
		soft.assertEquals(data, "Trichy");
		dp.click();


	}

	@Test(priority =2)
	public void enterDate() {
		WebElement cal = driver.findElement(By.xpath("//span[text()='Date of Journey']"));
		cal.click();
		WebElement date = driver.findElement(By.xpath("//span[text()='7']"));
		date.click();

	}

	@Test(priority =3, enabled=false)
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

	@Test(priority =5, invocationCount = 3)
	public void busTitle() {
		List<WebElement> buses = driver.findElements(By.xpath("//div[@class=\"travelsName___950ec8\"]"));
		for (WebElement bus : buses) {
			System.out.println(bus.getText());
		}
	}

	@AfterClass
	public static void quitBrowser() {
		soft.assertAll();
		driver.quit();

	}
}
