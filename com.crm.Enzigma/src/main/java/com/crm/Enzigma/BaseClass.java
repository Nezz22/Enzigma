package com.crm.Enzigma;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	public static WebDriver driver;
	String browser ="chrome";
	@BeforeClass
	public void preCondition() {
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		}else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		}else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if (browser.equals("safari")) {
			driver = new SafariDriver();
		}else if (browser.equals("internet explorer")) {
			driver = new InternetExplorerDriver();
		}else {
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://app-staging.nokodr.com/super/apps/auth/v1/index.html#/login");
		System.out.println("preCondition");
	}
}
