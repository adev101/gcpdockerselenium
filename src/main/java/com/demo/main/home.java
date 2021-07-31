package com.demo.main;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class home {

	static String mode=null, path=null, url=null;
	static WebDriver driver=null;
	
	public void getURL(int browser) throws IOException, InterruptedException {
		
		mode="remote";
		
		if(mode.contentEquals("local")) {
			
			path=System.getProperty("user.dir");
			
			switch (browser)
			{
			case 1: 
				System.setProperty("webdriver.chrome.driver", path+"//src//main//resources//drivers//chromedriver.exe");
				driver=new ChromeDriver();
				break;
			case 2: 
				System.setProperty("webdriver.edge.driver", path+"//src//main//resources//drivers//IEDriverServer.exe");
				driver= new InternetExplorerDriver();
				break;
			case 3:
				System.setProperty("webdriver.gecko.driver", path+"//src//main//resources//drivers//geckodriver.exe");
				driver= new FirefoxDriver();
				break;
			default:
				System.out.println("Wrong Browser");
				break;
			}
		}
		else if (mode.contentEquals("remote")) {
			DesiredCapabilities dr=null;
			dr=DesiredCapabilities.chrome();
			dr.setBrowserName("chrome");
//			dr.setPlatform(Platform.LINUX);
//			dr.setVersion("81.0.4044.138");
			
			System.out.println("Setting hub ip address");
			//String hub=TestConfig.getConfigDetails().get("hub");
			String hub="http://34.134.198.95:32369/wd/hub";
			
			System.setProperty("webdriver.chrome.driver", path+"//src//main//resources//drivers//chromedriver.exe");
			driver=new RemoteWebDriver(new URL(hub),dr);
			
			System.out.println("Created driver object");
			
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		url="https://hale-carport-321118.el.r.appspot.com";
		System.out.println(url);
		driver.get(url);
		driver.manage().window().maximize();
		
		if(driver.findElement(By.xpath("//body")).getText().contains("Hello, World")) {
			System.out.println("Test passed");
		}
		else {
			System.out.println("Test Failed");
			Assert.fail();
		}
		
		Thread.sleep(3000);
		driver.close();
		driver.quit();
		
	}
//	
//	
//	public void clickElement(String xpath, String textToSearch) {
//		driver.findElement(By.xpath(xpath)).sendKeys(textToSearch);
//		driver.findElement(By.xpath(xpath)).sendKeys(Keys.ENTER);
//	}
//	
}
