package testcases;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestTabsAndPopups {

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
		
		//Pre-conditions | Maximize the browser and apply implicit waits and.
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Navigate to Google.
		driver.get("https://google.com");
		System.out.println("First window --- TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Opens and switches focus to a new tab.
		driver.switchTo().newWindow(WindowType.TAB);
		System.out.println("Successfully opens and switches focus to new tab.");
		
		//In a new tab, navigate to Gmail.
		driver.get("https://gmail.com");
		System.out.println("Second window --- TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Click Sign-In Link.
		WebElement linkSignIn = driver.findElement(By.linkText("Mag-sign in"));
		linkSignIn.click();
		System.out.println("The Sign-in link has been successfully clicked and has opened a new tab after clicking.");
		
		//Manual switch by getting all window handles and based on those handles, we need to switch to the 3rd window.
		Set<String> windowIDs = driver.getWindowHandles();
		Iterator<String> iterate = windowIDs.iterator();
		
		//Since we have 3 opened windows, all in the same single browser so we need to iterate 3 times.
		iterate.next();
		iterate.next();
		String third_window = iterate.next();
		
		//Switch now to third window.
		driver.switchTo().window(third_window);
		System.out.println("Successfully switched to 3rd window.");
		System.out.println("Third window --- TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
		
		//Opens and switches focus to a new, separate window.
		driver.switchTo().newWindow(WindowType.WINDOW);
		System.out.println("Successfully opens and switches focus to a new, separate window.");
		
		//This is the 4th window and navigate to another site.
		driver.get("http://way2automation.com/");
		System.out.println("Fourth window --- TITLE: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());
	}

}
