import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.locators.RelativeLocator;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestRelativeLocators {

	public static String browser = "chrome"; //excel sheet
	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		} else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		} else if(browser.equals("ie")) {			
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			
		} else if(browser.equals("edge")) {			
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		
		} else if(browser.equals("opera")) {			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			OperaOptions options = new OperaOptions();
			options.setBinary("C:\\Users\\hello\\AppData\\Local\\Programs\\Opera\\64.0.3417.73\\opera.exe");
			capabilities.setCapability(OperaOptions.CAPABILITY, options);
			
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver(options);
		
		}
		
		//Pre-conditions | Maximize the browser and apply implicit waits.
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Navigate to this site for sample locators.
		driver.get("http://way2automation.com/way2auto_jquery/index.php");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Fill-in the Email Address field.
		WebElement fieldEmail = driver.findElement(By.xpath("//input[@name='email']"));
		fieldEmail.sendKeys("michikodaimon@tv-asahi.co.jp");
		
		//Click the Submit button.
		WebElement buttonSubmit = driver.findElement(By.xpath("(//input[@value='Submit'])[2]"));
		buttonSubmit.click();
		System.out.println("RUN 1: Successfully clicked the Submit button");
		
		//Navigate to this site for sample locators
		driver.get("http://way2automation.com/way2auto_jquery/index.php");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Fill-in the Email Address field.
		WebElement fieldEmail1 = driver.findElement(By.xpath("//input[@name='email']"));
		fieldEmail1.sendKeys("michikodaimon@tv-asahi.co.jp");
		
		//Click the Submit button. Use of relative locators for Selenium 4, right of a locator element.
		driver.findElement(RelativeLocator.withTagName("input").toRightOf(By.linkText("Signin"))).click();
		System.out.println("RUN 2: Successfully clicked the Submit button");
		
		//Navigate to this site for sample locators
		driver.get("http://way2automation.com/way2auto_jquery/index.php");
		System.out.println("TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Fill-in the Email Address field.
		WebElement fieldEmail2 = driver.findElement(By.xpath("//input[@name='email']"));
		fieldEmail2.sendKeys("michikodaimon@tv-asahi.co.jp");
		
		//Click the Submit button. Use of relative locators for Selenium 4, below of a locator element.
		driver.findElement(RelativeLocator.withTagName("input").below(By.xpath("(//input[@name='password'])[2]"))).click();
		System.out.println("RUN 3: Successfully clicked the Submit button");
		
	}

}
