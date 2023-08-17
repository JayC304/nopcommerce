package user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class User_01_Register {
	WebDriver driver;
	String emailAddress;
	String projectPath = System.getProperty("user.dir");

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {

		if (browserName.equals("firefox")) {
			// System.setProperty("webdriver.gecko.driver", projectPath +
			// "\\browserDrivers\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Browser name invalid.");
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		emailAddress = "anhtran" + generateFakeNumber() + "@gmail.vn";
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("span#FirstName-error")).getText(),
				"First name is required.");
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		// test git
	}

	@Test
	public void TC_03_Register_Success() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Anh");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Tran");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");

		driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

		// driver.findElement(By.cssSelector("a.ico-logout")).click();
	}

	@Test
	public void TC_04_Register_Existing_Email() {
	}

	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
